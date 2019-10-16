package commands;

import java.io.*;
public class Insert
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

    public void InsertQuery(String words[],String str)
    {
        if(words.length<=3)
        {
            System.out.println("Wrong Query");
            return;
        }
        // if(checkCharacter(str))
        // {
        //     System.out.println("Wrong Query");
        //     return ;
        // }
        if(!words[1].equals("into"))
        {
            System.out.println("Missing into Keyword");
            return ;
        }
        else
        {
            String tablename = words[2];
            String efile = "db/"+tablename+".txt";
            File f =  new File(efile);
            if(!f.exists())
            {
                System.out.println("Table Donot Exist");
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
                String checkValueSyntax;//Checking if values is written or not
                if(str.charAt(openingBrace-1)==' ')
                {
                    checkValueSyntax = words[3];
                }
                else
                {
                    checkValueSyntax="";
                    int i;
                    for(i=0;i<words[3].length();i++)
                    {
                        if(words[3].charAt(i)=='(')break;
                        else checkValueSyntax=checkValueSyntax+words[3].charAt(i);
                    }
                }
                if(!checkValueSyntax.equals("values"))
                {
                    System.out.println("Incorrect value Syntax");
                    return ;
                }
                String colstr="";
                for(int i=openingBrace+1;i<closingBrace;i++)
                {
                    colstr=colstr+str.charAt(i);
                }
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

                // checking if the col are same as schema of the table
                String filename = "";
                filename+="db/"+tablename+".txt";
                int cntcol=0;
                try
                {
                    FileReader fr = new FileReader(filename);
                    BufferedReader br = new BufferedReader(fr);
                    String line = br.readLine();
                    System.out.println(line);
                    
                    for(int i=0;i<line.length();i++)
                    {
                        if(line.charAt(i)=='|')
                        cntcol++;
                    }
                    fr.close();
                }
                catch(Exception e)
                {   
                    System.out.println(e);
                }
                if(colnames.length != cntcol-1)
                {
                    System.out.println("Wrong No of col Entries");
                    return;
                }
                for(int i=0;i<colnames.length;i++)
                {
                    colnames[i]=colnames[i].trim();
                    System.out.println(colnames[i]);
                }
               
                try
                {
    
                    FileWriter fw=new FileWriter(filename,true);
                    // fw.write(tablename+"|");
                    for(int i=0;i<colnames.length;i++)
                    {
                        fw.write(colnames[i]+"|");
                    }   
                    fw.write("\n");
                    fw.close();
                    System.out.println("Row Entered Created");
                }  
                catch(Exception e)
                {
                    System.out.println(e);
                }  
            }
        }
    }
}