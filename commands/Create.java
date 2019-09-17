package commands;
import java.util.*;
import java.io.*;
public class Create
{
    public  void createtable(String words[],String str)
    {
        if(!words[1].equals("table"))
        {
            System.out.println("Missing table Keyword");
            return ;
        }
        else
        {
            int openingBrace = str.indexOf('(');
            int closingBrace  = str.indexOf(')');
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
        }
    }
}