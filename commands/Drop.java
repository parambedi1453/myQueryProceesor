package commands;
import java.io.*;
public class Drop
{
    public boolean checkCharacter(String str)
    {
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)=='!'||str.charAt(i)=='@'||str.charAt(i)=='#'||str.charAt(i)=='$'||str.charAt(i)=='%'||str.charAt(i)=='^'||str.charAt(i)=='&')
            return true;
        }
        return false;
    }
    public void dropQuery(String words[],String str)
    {
        if(words.length>3)
        {
            System.out.println("Wrong Query Eneterd");
            return ;
        }
        if(checkCharacter(str))
        {
            System.out.println("Wrong Query");
            return ;
        }
        if(!words[1].equals("table"))
        {
            System.out.println("Wrong Query Entered");
            return;
        }
        else
        {
            String tablename = words[2];
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
                    boolean ans  = f.delete();
                    if(ans)
                    System.out.println("Table deleted");
                }   
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        }
    }
}