import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class lab2_count_keywordsTest {
	private static lab2_count_keywords countKeyWords = new lab2_count_keywords();
    String []keywords=  {"auto","double","int","struct","break","else","long","switch",
            "case","enum","register","typedef","char","extern","return","union",
            "const","float","short","unsigned","continue","for","signed","void",
            "default","goto","sizeof","volatile","do","if","while","static","elseif"};
    String []code_arr;
	@Before
	public void setUp() throws Exception {
	    String path = "C:\\Users\\´óÏ¹Õº½´\\Desktop\\²âÊÔ´úÂë\\JUnit.c";
	    String reader;
	    FileInputStream inputStream = new FileInputStream(path);
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder ss = new StringBuilder();
        while ((reader = bufferedReader.readLine())!=null){
            reader = reader.replace(':', ' ');
            if(reader.matches("(.*)//(.*)")){
                String b[] = reader.split("//");
                ss.append(b[0]+" ");
            }else{
                ss.append(reader+" ");
            }
        }
        inputStream.close();
        bufferedReader.close();

        //Rebuild the content which was read the former
        String s = ss.toString();
        Pattern p = Pattern.compile("\"(.*?)\"");
        Matcher m = p.matcher(s);
        while(m.find()){
            s=s.replace(m.group()," ");
            p=Pattern.compile("\"(.*?)\"");
            m=p.matcher(s);
        }
        p=Pattern.compile("/\\**(.*?)/");
        m=p.matcher(s);
        while(m.find()){
            s=s.replace(m.group()," ");
            m=p.matcher(s);
        }
        if(s.isEmpty())
        {System.out.println("Wrong Format");
            System.exit(0);
        }
        s=s.replace("["," ");
        s=s.replace("]"," ");
        s=s.replace("-"," ");
        s=s.replace("*"," ");
        s=s.replace("/"," ");
        s=s.replace("+"," ");
        s=s.replace(">"," ");
        s=s.replace("="," ");
        s=s.replace("!"," ");
        s=s.replace(":"," ");
        s=s.replace("\\"," ");
        s= s.replaceAll("[^a-zA-Z]", " ");
        code_arr=s.split("[  ' ']");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFirst_Level() {
		assertEquals(countKeyWords.First_Level(code_arr, keywords),21);
	}

	@Test
	public void testSecond_Level() {
		assertEquals(countKeyWords.Second_Level(code_arr, keywords),1);
	}

	@Test
	public void testThird_Level() {
		assertEquals(countKeyWords.Third_Level(code_arr, keywords),2);
	}

	@Test
	public void testFourth_Level() {
		assertEquals(countKeyWords.Fourth_Level(code_arr, keywords),0);
	}

}
