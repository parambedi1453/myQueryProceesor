package commands;
import java.io.*;
public class Select
{
    public int m;
    public boolean checkCharacter(String str)
    {
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)=='!'||str.charAt(i)=='#'||str.charAt(i)=='$'||str.charAt(i)=='%'||str.charAt(i)=='^'||str.charAt(i)=='&')
            return true;

            
        }
        return false;
    }
    public void selectQuery(String words[],String str)
    {
        if(words.length<=3)
        {
            System.out.println("Wrong Query");
            return;
        }
        if(checkCharacter(str))
        {
            System.out.println("Wrong Query");
            return ;
        }
       for(int i=0;i<words.length;i++)
       {
        if(words[i].equals("from"))
        {
            m=i;
            break;
        }
           
       }
        String tablename = words[m+1];
        String efile = "db/"+tablename+".txt";
        File f =  new File(efile);
        if(!f.exists())
        {
            System.out.println("Table Donot Exist");
            return ;
        }

        if(m==2 && words[1].equals("*"))
        {
            //display the wholle table;
            String filename = "";
                filename+="db/"+tablename+".txt";
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
        else
        {
            String colstr="";
            for(int j=1;j<m;j++)
            {
                colstr=colstr+words[j];
            }

            colstr = colstr.trim();//trimimg the front and the back spaces
            colstr = colstr.replaceAll(" +"," ");//removing the multiple spaces from the colstr
            System.out.println(colstr);
            

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

            String colnames[] = colstr.split(",");//Seperating the columns based on ,
            for(int k=0;k<colnames.length;k++)
            {
                colnames[k]=colnames[k].trim();
                System.out.println(colnames[k]);
            }
            String filename = "";
            filename+="db/"+tablename+".txt";
            try
            {
                FileReader fr = new FileReader(filename);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                line=line+ br.readLine();
                System.out.println(line);
                
                fr.close();
                return;
            }
            catch(Exception e)
            {
                System.out.println(e);
            }

        }
           
    }
}