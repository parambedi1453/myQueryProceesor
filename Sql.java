import java.util.*;
import commands.*;
import java.io.*;
public class Sql
{

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Sql Command = ");
        String str = sc.nextLine();
       
        str = str.trim();//trimming leading and trailing spaces
        str = str.toLowerCase();//Converting the given Query to lowerCase;
        str = str.replaceAll(" +"," ");//to replace multiple Spaces with single space

        //The String is Modified as
        System.out.println(str);
        
        String words[] = str.split("\\s");
        // for(int i=0;i<words.length;i++)
        // {
        //     System.out.println(words[i]);
        // }
       
        if(words[0].equals("quit"))
        {   
            System.exit(0);
        }
        else if(words[0].equals("print"))
        {
            Print pob = new Print();
            pob.printQuery(words,str);
        }
        else
        {
            if(words.length<3)
                System.out.println("Wrong Query Entered");
            else
            {
                if(words[0].equals("create"))
                {
                    Create crOb = new Create();
                    crOb.createtable(words,str);
                }
                if(words[0].equals("insert"))
                {
                    Insert irOb = new Insert();
                    irOb.InsertQuery(words,str);
                }
                if(words[0].equals("select"))
                {
                    Select sob = new Select();
                    sob.selectQuery(words,str);
                }
                if(words[0].equals("drop"))
                {
                    Drop dob = new Drop();
                    dob.dropQuery(words,str);
                }
            }
        }
        
    }
}