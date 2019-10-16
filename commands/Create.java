package commands;
import java.io.*;
public class Create
{
    public boolean checkCharacter(String str)
    {
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)=='!'||str.charAt(i)=='#'||str.charAt(i)=='$'||str.charAt(i)=='%'||str.charAt(i)=='^'||str.charAt(i)=='&'||str.charAt(i)=='*')
            return true;

            
        }
        return false;
    }
    public  void createtable(String words[],String str)
    {
        if(checkCharacter(str))
        {
            System.out.println("Wrong Query");
            return ;
        }
        if(!words[1].equals("table"))
        {
            System.out.println("Missing table Keyword");
            return ;
        }
        else
        {
            int openingBrace = str.indexOf('(');
            int closingBrace  = str.indexOf(')');
            if(openingBrace==-1 || closingBrace==-1)
            {
                System.out.println("Wrong Query");
                return;
            }
            if(str.length()>closingBrace+2)
            {
                System.out.println("Wrong Query");
                return;
            }
            if(closingBrace+1<str.length())
            {
                if(str.charAt(closingBrace+1)==' ')
                {
                    if(closingBrace+2<str.length() && str.charAt(closingBrace+2)!=';')
                    {
                        System.out.println("Wrong Query");
                        return ;
                    }
                }
            }
            String tablename;//String containing the tablename
            String colstr;//String containing (name,rollno,age ) this part of String 
            if(str.charAt(openingBrace-1)==' ')
            {
                tablename = words[2];
            }
            else
            {
                tablename="";
                int i;
                for(i=0;i<words[2].length();i++)
                {
                    if(words[2].charAt(i)=='(')break;
                    else tablename=tablename+words[2].charAt(i);
                }
                
            }
            // Check if the file already exists or not
            String efile = "db/"+tablename+".txt";
            File f =  new File(efile);
            if(f.exists())
            {
                System.out.println("Table Already Exits");
                return ;
            }

            colstr="";
            for(int i=openingBrace+1;i<closingBrace;i++)
            {
                colstr=colstr+str.charAt(i);
            }
            //Extracted tablename from Query
            System.out.println(tablename);
            System.out.println(colstr);
            
            colstr = colstr.trim();
            colstr = colstr.replaceAll(" +"," ");
            //Conditions for checking the column query

            for(int i=0;i<colstr.length();i++)
            {
                if(colstr.charAt(0)==',')
                {
                    System.out.println("WRONG QUERY");
                    return ;
                }
                else if(colstr.charAt(i)==',')
                {
                    if((i<colstr.length()-2)&& (colstr.charAt(i+1)==','||colstr.charAt(i+2)==','))
                    {
                        System.out.println("WRONG QUERY");
                        return;
                    }
                }
            }
            String colnames[] = colstr.split(",");//Sepating the colnames based on comma
            for(int i=0;i<colnames.length;i++)
            {
                colnames[i]=colnames[i].trim();
                System.out.println(colnames[i]);
            }
            String filename = "";
            filename+="db/"+tablename+".txt";
            try
            {

                FileWriter fw=new FileWriter(filename);
                fw.write(tablename+"|");
                for(int i=0;i<colnames.length;i++)
                {
                    fw.write(colnames[i]+"|");
                }  
                fw.write("\n");
                fw.close();
                System.out.println("Table Created");
            }  
            catch(Exception e)
            {
                System.out.println(e);
            }  
        }
    }
}