import com.example.practicalfive.TextProcessor;
public class TextProcessorCheck {
 public static void main(String[] args) {
  TextProcessor processor = new TextProcessor();
  String[][] cases = {{null,null},{"",null},{"   ",null},{"Guangzhou",null},{"Beijing","Beijing"},{"beijing","Beijing"},{"BEIJING","Beijing"},{"SHANGHAI","Shanghai"},{"tianjin","Tianjin"},{"ChOnGqInG","Chongqing"},{"  Beijing  ","Beijing"}};
  for(String[] item:cases) {
   String result=processor.processMunicipality(item[0]);
   if(!java.util.Objects.equals(result,item[1])) throw new AssertionError(item[0]+" => "+result);
  }
  System.out.println("PASS: 11 validation cases");
 }
}