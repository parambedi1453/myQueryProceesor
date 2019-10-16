package commands;
import java.io.*;
public class Print
{
    public boolean checkCharacter(String str)
    {
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)=='!'||str.charAt(i)=='@'||str.charAt(i)=='#'||str.charAt(i)=='$'||str.charAt(i)=='%'||str.charAt(i)=='^'||str.charAt(i)=='&'||str.charAt(i)=='*')
            return true;            
        }
        return false;
    }
    public void printQuery(String words[],String str)
    {
        if(checkCharacter(str))
        {
            System.out.println("Wrong Query");
            return ;
        }
        if(words.length>=3)
        {
            System.out.println("Wrong Query Entered");
            return;
        }
        else
        {
            String tablename = words[1];
            String filename = "db/"+tablename+".txt";
                File f =  new File(filename);
                if(!f.exists())
                {
                    System.out.println("Table Donot Exist");
                    return ;
                }
                else
                {
                    try
                    {
                        FileReader fr = new FileReader(filename);
                        BufferedReader br = new BufferedReader(fr);
                        String line;
                        while((line=br.readLine())!=null)
                        {
                            System.out.println(line);
                        }
                        fr.close();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                    return;
                
                }
        }

    }
    
}