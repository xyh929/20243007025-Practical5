"""Run the assignment input cases on a connected Android emulator using ADB.
Usage: python verification/runtime_check.py --adb PATH --output DIRECTORY
Requires an already-built debug APK. Does not change application source.
"""
import argparse, json, re, subprocess, time
from pathlib import Path
import xml.etree.ElementTree as ET
parser=argparse.ArgumentParser()
parser.add_argument('--adb',required=True)
parser.add_argument('--output',required=True)
a=parser.parse_args()
out=Path(a.output);out.mkdir(parents=True,exist_ok=True)
def adb(*args):
    return subprocess.check_output([a.adb,*args],stderr=subprocess.STDOUT).decode('utf-8',errors='replace')
def dump():
    adb('shell','uiautomator','dump','/sdcard/practical5-ui.xml')
    return ET.fromstring(adb('shell','cat','/sdcard/practical5-ui.xml'))
def node(root,id):
    return root.find(".//node[@resource-id='com.example.practicalfive:id/"+id+"']")
def tap(n):
    coords=list(map(int,re.findall(r'\d+',n.attrib['bounds'])))
    adb('shell','input','tap',str((coords[0]+coords[2])//2),str((coords[1]+coords[3])//2))
def shot(name):
    remote='/sdcard/practical5-check.png'
    adb('shell','screencap','-p',remote)
    adb('pull',remote,str(out/name))
adb('install','-r','app/build/outputs/apk/debug/app-debug.apk')
results=[]
for value,city in [('Beijing','Beijing'),('beijing','Beijing'),('BEIJING','Beijing'),('Shanghai','Shanghai'),('Tianjin','Tianjin'),('Chongqing','Chongqing'),('ChOnGqInG','Chongqing'),('Guangzhou',None),('',None),('%s%s%s',None)]:
    adb('shell','am','force-stop','com.example.practicalfive')
    adb('shell','am','start','-f','0x10008000','-n','com.example.practicalfive/.MainActivity')
    time.sleep(0.7)
    root=dump();tap(node(root,'municipality_input'))
    if value: adb('shell','input','text',value)
    adb('shell','input','keyevent','66')
    time.sleep(0.5)
    root=dump()
    if city:
        title=node(root,'municipality_name'); info=node(root,'municipality_information')
        assert title is not None and title.attrib['text']==city,(value,'wrong title')
        assert info is not None and info.attrib['text'].startswith(city),(value,'wrong summary')
        assert node(root,'municipality_image') is not None
        button=node(root,'visit_site'); assert button is not None
        shot(city.lower()+'.png')
        adb('logcat','-c');tap(button);time.sleep(0.7)
        logs=adb('logcat','-d','-s','ActivityTaskManager:I')
        host={'Beijing':'english.beijing.gov.cn','Shanghai':'english.shanghai.gov.cn','Tianjin':'en.tj.gov.cn','Chongqing':'english.cq.gov.cn'}[city]
        assert 'android.intent.action.VIEW' in logs and host in logs,(value,logs)
        (out/(city.lower()+'-website-intent.txt')).write_text(logs,encoding='utf-8')
        results.append({'input':value,'city':city,'title_summary_image_button':'PASS','website_intent':host})
    else:
        assert node(root,'municipality_input') is not None and node(root,'municipality_name') is None,(value,'navigated unexpectedly')
        # Android error popups are omitted from UIAutomator XML; capture them for visual review.
        adb('shell','input','keyevent','66')
        expected='Please enter a municipality name.' if value in ('','%s%s%s') else 'Choose Beijing, Shanghai, Tianjin or Chongqing.'
        shot('invalid-'+('empty' if not value else 'spaces' if value=='%s%s%s' else 'unsupported')+'.png')
        results.append({'input':'   ' if value=='%s%s%s' else value,'expected_error_message_for_screenshot_review':expected,'stays_on_main':'PASS'})
    print('PASS',repr(value),flush=True)
(out/'results.json').write_text(json.dumps(results,indent=2),encoding='utf-8')
print('PASS: all 10 runtime cases; website checks verify Android browser handoff, not page rendering.')
