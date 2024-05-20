import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class PokemonHomeListV5 
{
    public static final int POKEDEX_SIZE = 1026; // 1025 + 1 for Indexing ----- Size of names.txt

    public static PokedexEntry[] data = new PokedexEntry[POKEDEX_SIZE];

    public static int counter = 1;
    public static int tempTrack = 0;

    public static final File input = new File("names.txt");
    public static final File output = new File("FinalListV5.txt");
    public static final File imageLinks = new File("imageLinks.txt");

    public static void main(String[] args)
    {
        for(int i = 0; i < POKEDEX_SIZE; i++)
        {
            data[i] = new PokedexEntry();
        }

        readAndSetData();
        writeImageLinks();
        outputData();
        convertToCsv();

        System.out.println("Finished!");
    }

    public static void readAndSetData()
    {
        try
        {
            Scanner fileReader = new Scanner(input);
            System.out.println("File opened Successfully.");

            for(int i = 1; fileReader.hasNextLine() && i <= POKEDEX_SIZE; i++)
            {
                data[i].setName(fileReader.nextLine());
            }

            fileReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("There was an error opening the input file.");
            System.exit(0);
        }

        setGenders();
        setForms();
        setRegionalForms();
        //setGmaxForms();
    }

    public static void writeImageLinks()
    {
        try
        {
            FileWriter linkWriter = new FileWriter(imageLinks);

            for(int i = 1; i < POKEDEX_SIZE; i++)
            {
                if(i != 774) //Anything but Minior
                {
                    linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + ".png" + '\n');
                    counter++;
                }
                else
                {
                    linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-7.png" + '\n');
                    counter++;
                }
                
                if(i != 774 && i != 869) //Anything but Minior  Alcremie
                {
                    linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + ".png" + '\n');
                    counter++;
                }

                if(data[i].getHasGender())
                {
                    linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-female.png" + '\n');
                    counter++;
                    linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-female.png" + '\n');
                    counter++;
                }

                if(data[i].getHasAltForms())
                {
                    int alcremieColor = 0; //Alcremie Color
                    int alcremieTopping = 1; //Alcremie Topping
                    
                    for(int j = 1; j <= (data[i].getAltForms()); j++)
                    {
                        if (i == 25) //Pikachu
                        {
                            linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-" + j + ".png" + '\n');
                            counter++;
                        }
                        else if (i == 774) //Minior
                        {
                            linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-" + (j + 7) + ".png" + '\n');
                            counter++;

                            if (j == 6)
                            {
                                linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + (j + 7) + ".png" + '\n');
                                counter++;
                            }
                        }
                        else if (i == 869) //Alcremie
                        {
                            linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-" + alcremieColor + "-" + alcremieTopping + ".png" + '\n');
                            counter++;
                            alcremieTopping++;

                            if (alcremieColor == 8 && alcremieTopping == 7)
                            {
                                linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + ".png" + '\n');
                                counter++;
                                linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 0 + "-" + 1 + ".png" + '\n');
                                counter++;
                                linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 0 + "-" + 2 + ".png" + '\n');
                                counter++;
                                linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 0 + "-" + 3 + ".png" + '\n');
                                counter++;
                                linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 0 + "-" + 4 + ".png" + '\n');
                                counter++;
                                linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 0 + "-" + 5 + ".png" + '\n');
                                counter++;
                                linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 0 + "-" + 6 + ".png" + '\n');
                                counter++;
                            }
                            else if(alcremieTopping == 7)
                            {
                                alcremieTopping = 0;
                                alcremieColor++;
                            }
                        }
                        else
                        {
                            linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-" + j + ".png" + '\n');
                            counter++;
                            linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + j + ".png" + '\n');
                            counter++;
                        }
                    }
                }

                if (data[i].getHasAlolanForm())
                {
                    linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-" + 1 + ".png" + '\n');
                    counter++;
                    linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 1 + ".png" + '\n');
                    counter++;
                }

                if (data[i].getHasGalarianForm())
                {
                    if(i == 52) //Galarian Meowth
                    {
                        linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-" + 2 + ".png" + '\n');
                        counter++;
                        linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 2 + ".png" + '\n');
                        counter++;
                    }
                    else
                    {
                        linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-" + 1 + ".png" + '\n');
                        counter++;
                        linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 1 + ".png" + '\n');
                        counter++;
                    }
                }

                if (data[i].getHasHisuianForm())
                {
                    linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-" + 1 + ".png" + '\n');
                    counter++;
                    linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 1 + ".png" + '\n');
                    counter++;

                    if(i == 215) //Hisuian Sneasel
                    {
                        linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-" + 1 + "-female.png" + '\n');
                        counter++;
                        linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 1 + "-female.png" + '\n');
                        counter++;
                    }
                }

                if (data[i].getHasPaldeanForm())
                {
                    linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-" + 1 + ".png" + '\n');
                    counter++;
                    linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 1 + ".png" + '\n');
                    counter++;

                    if(i == 128) //Paldean Tauros
                    {
                        linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-" + 2 + ".png" + '\n');
                        counter++;
                        linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 2 + ".png" + '\n');
                        counter++;
                        linkWriter.write("https://www.centropkmn.com/pokes/home/" + i + "-" + 3 + ".png" + '\n');
                        counter++;
                        linkWriter.write("https://www.centropkmn.com/pokes/home/shiny/" + i + "-" + 3 + ".png" + '\n');
                        counter++;
                    }
                }
            }

            linkWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("There was an error opening the output file.");
            System.exit(0);
        }
    }

    public static void outputData()
    {
        try
        {
            FileWriter myWriter = new FileWriter(output);

            for(int i = 1; i < POKEDEX_SIZE; i++)
            {
                if(i != 201 && i != 412 && i != 413 && i != 422 && i != 423 && i != 492 && i != 550 && i != 585 && i != 586 && i != 641 && i != 642 && i != 645 && 
                   i != 666 && i != 669 && i != 670 && i != 671 && i != 676 && i != 710 && i != 711 && i != 741 && i != 745 && i != 774 && i != 849 && i != 854 && 
                   i != 855 && i != 869 && i != 892 && i != 905 && i != 982 && i != 925 && i != 978 && i != 931 && i != 999 && i != 1012 && i != 1013)
                {
                    myWriter.write(data[i].getName() + '\n');
                    counter++;
                    myWriter.write("Shiny " + data[i].getName() + '\n');
                    counter++;
                }            

                /*
                if(i != 774 && i != 869) //Anything but Minior  Alcremie
                {
                    myWriter.write("Shiny " + data[i].getName() + '\n');
                    counter++;
                }
                */

                if(data[i].getHasGender())
                {
                    myWriter.write("Female " + data[i].getName() + '\n');
                    counter++;
                    myWriter.write("Shiny Female " + data[i].getName() + '\n');
                    counter++;
                }

                if(data[i].getHasAltForms())
                {
                    boolean uniqueFirstFormNameFlag = false;

                    for(int j = 2; j <= (data[i].getAltForms() + 1); j++)
                    {
                        //Before All Unique
                        if(i == 412 || i == 413 || i == 422 || i == 423 || i == 492 || i == 550 || i == 585 || i == 586 || i == 666 || i == 669 || 
                           i == 670 || i == 671 || i == 676 || i == 710 || i == 711 || i == 741 || i == 745 || i == 849 || i == 854 || i == 855 || 
                           i == 892 || i == 982 || i == 978 || i == 931 || i == 999 || i == 1012 || i == 1013)
                        {
                            if(uniqueFirstFormNameFlag == false)
                            {
                                myWriter.write(data[i].getFormName(j - 2) + data[i].getName() + '\n');
                                counter++;
                                myWriter.write("Shiny " + data[i].getFormName(j - 2) + data[i].getName() + '\n');
                                counter++;

                                uniqueFirstFormNameFlag = true;
                            }

                            myWriter.write(data[i].getFormName(j - 1) + data[i].getName() + '\n');
                            counter++;
                            myWriter.write("Shiny " + data[i].getFormName(j - 1) + data[i].getName() + '\n');
                            counter++;
                        }
                        //Before Additional
                        else if(i == 801 || i == 893 || i == 901)
                        {
                            myWriter.write(data[i].getFormName(j - 2) + data[i].getName() + '\n');
                            counter++;
                            myWriter.write("Shiny " + data[i].getFormName(j - 2) + data[i].getName() + '\n');
                            counter++;
                        }
                        //After All Unique
                        else if(i == 201 || i == 641 || i == 642 || i == 645 || i == 905 || i == 925)
                        {
                            if(uniqueFirstFormNameFlag == false)
                            {
                                myWriter.write(data[i].getName() + data[i].getFormName(j - 2) + '\n');
                                counter++;
                                myWriter.write("Shiny " + data[i].getName() + data[i].getFormName(j - 2) + '\n');
                                counter++;

                                uniqueFirstFormNameFlag = true;
                            }

                            myWriter.write(data[i].getName() + data[i].getFormName(j - 1) + '\n');
                            counter++;
                            myWriter.write("Shiny " + data[i].getName() + data[i].getFormName(j - 1) + '\n');
                            counter++;
                        }
                        //After Additional
                        else if(i == 479)
                        {
                            myWriter.write(data[i].getName() + data[i].getFormName(j - 2) + '\n');
                            counter++;
                            myWriter.write("Shiny " + data[i].getName() + data[i].getFormName(j - 2) + '\n');
                            counter++;
                        }
                        //Pikachu
                        else if(i == 25)
                        {
                            myWriter.write(data[i].getFormName(j - 2) + data[i].getName() + '\n');
                            counter++;
                        }
                        //Minior
                        else if(i == 774)
                        {
                            myWriter.write(data[i].getFormName(j - 2) + data[i].getName() + '\n');
                            counter++;

                            if (j == 8)
                            {
                                myWriter.write("Shiny " + data[i].getName() + '\n');
                                counter++;
                            }
                        }
                        //Alcremie
                        else if(i == 869)
                        {
                            myWriter.write(data[i].getFormName(9) + data[i].getFormName(j - 2) + data[i].getName() + '\n');
                            counter++;
                            myWriter.write(data[i].getFormName(10) + data[i].getFormName(j - 2) + data[i].getName() + '\n');
                            counter++;
                            myWriter.write(data[i].getFormName(11) + data[i].getFormName(j - 2) + data[i].getName() + '\n');
                            counter++;
                            myWriter.write(data[i].getFormName(12) + data[i].getFormName(j - 2) + data[i].getName() + '\n');
                            counter++;
                            myWriter.write(data[i].getFormName(13) + data[i].getFormName(j - 2) + data[i].getName() + '\n');
                            counter++;
                            myWriter.write(data[i].getFormName(14) + data[i].getFormName(j - 2) + data[i].getName() + '\n');
                            counter++;
                            myWriter.write(data[i].getFormName(15) + data[i].getFormName(j - 2) + data[i].getName() + '\n');
                            counter++;

                            if(j == 10)
                            {
                                myWriter.write(data[i].getFormName(9) + "Shiny " + data[i].getName() + '\n');
                                counter++;
                                myWriter.write(data[i].getFormName(10) + "Shiny " + data[i].getName() + '\n');
                                counter++;
                                myWriter.write(data[i].getFormName(11) + "Shiny " + data[i].getName() + '\n');
                                counter++;
                                myWriter.write(data[i].getFormName(12) + "Shiny " + data[i].getName() + '\n');
                                counter++;
                                myWriter.write(data[i].getFormName(13) + "Shiny " + data[i].getName() + '\n');
                                counter++;
                                myWriter.write(data[i].getFormName(14) + "Shiny " + data[i].getName() + '\n');
                                counter++;
                                myWriter.write(data[i].getFormName(15) + "Shiny " + data[i].getName() + '\n');
                                counter++;
                            }
                        }
                        else
                        {
                            myWriter.write(data[i].getName() + " Form " + j + '\n');
                            counter++;
                            myWriter.write("Shiny " + data[i].getName() + " Form " + j + '\n');
                            counter++;
                        }

                        /*
                        if (i == 25) //Pikachu
                        {
                            myWriter.write(data[i].getName() + " Form " + j + '\n');
                            counter++;
                        }
                        else if (i == 774) //Minior
                        {
                            myWriter.write(data[i].getName() + " Form " + j + '\n');
                            counter++;

                            if (j == 7)
                            {
                                myWriter.write("Shiny " + data[i].getName() + '\n');
                                counter++;
                            }
                        }
                        else if (i == 869) //Alcremie
                        {
                            myWriter.write(data[i].getName() + " Form " + j + '\n');
                            counter++;

                            if (j == 63)
                            {
                                myWriter.write("Shiny " + data[i].getName() + " Form " + 1 + '\n');
                                counter++;
                                myWriter.write("Shiny " + data[i].getName() + " Form " + 2 + '\n');
                                counter++;
                                myWriter.write("Shiny " + data[i].getName() + " Form " + 3 + '\n');
                                counter++;
                                myWriter.write("Shiny " + data[i].getName() + " Form " + 4 + '\n');
                                counter++;
                                myWriter.write("Shiny " + data[i].getName() + " Form " + 5 + '\n');
                                counter++;
                                myWriter.write("Shiny " + data[i].getName() + " Form " + 6 + '\n');
                                counter++;
                                myWriter.write("Shiny " + data[i].getName() + " Form " + 7 + '\n');
                                counter++;
                            }
                        }
                        else
                        {
                            myWriter.write(data[i].getName() + " Form " + j + '\n');
                            counter++;
                            myWriter.write("Shiny " + data[i].getName() + " Form " + j + '\n');
                            counter++;
                        }
                        */
                    }
                }

                if (data[i].getHasAlolanForm())
                {
                    myWriter.write("Alolan " + data[i].getName() + '\n');
                    counter++;
                    myWriter.write("Shiny Alolan " + data[i].getName() + '\n');
                    counter++;
                }

                if (data[i].getHasGalarianForm())
                {
                    myWriter.write("Galarian " + data[i].getName() + '\n');
                    counter++;
                    myWriter.write("Shiny Galarian " + data[i].getName() + '\n');
                    counter++;
                }

                if (data[i].getHasHisuianForm())
                {
                    myWriter.write("Hisuian " + data[i].getName() + '\n');
                    counter++;
                    myWriter.write("Shiny Hisuian " + data[i].getName() + '\n');
                    counter++;

                    if(i == 215) //Hisuian Sneasel
                    {
                        myWriter.write("Female Hisuian " + data[i].getName() + '\n');
                        counter++;
                        myWriter.write("Shiny Female Hisuian " + data[i].getName() + '\n');
                        counter++;
                    }
                }

                if (data[i].getHasPaldeanForm())
                {
                    myWriter.write("Paldean " + data[i].getName() + '\n');
                    counter++;
                    myWriter.write("Shiny Paldean " + data[i].getName() + '\n');
                    counter++;

                    if(i == 128) //Paldean Tauros
                    {
                        myWriter.write("Paldean " + data[i].getName() + " Form 2" + '\n');
                        counter++;
                        myWriter.write("Shiny Paldean " + data[i].getName() + " Form 2" + '\n');
                        counter++;
                        myWriter.write("Paldean " + data[i].getName() + " Form 3" + '\n');
                        counter++;
                        myWriter.write("Shiny Paldean " + data[i].getName() + " Form 3" + '\n');
                        counter++;
                    }
                }

                if (data[i].getHasGmaxForm())
                {
                    if (i == 892)
                    {
                        myWriter.write("Gigantamax " + data[i].getName() + '\n');
                        counter++;
                        myWriter.write("Shiny Gigantamax " + data[i].getName() + '\n');
                        counter++;

                        myWriter.write("Gigantamax " + data[i].getName() + " Form 2" + '\n');
                        counter++;
                        myWriter.write("Shiny Gigantamax " + data[i].getName() + " Form 2" + '\n');
                        counter++;
                    }                
                    else
                    {
                        myWriter.write("Gigantamax " + data[i].getName() + '\n');
                        counter++;
                        myWriter.write("Shiny Gigantamax " + data[i].getName() + '\n');
                        counter++;
                    }
                }
            }

            myWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("There was an error opening the output file.");
            System.exit(0);
        }
    }

    public static void convertToCsv()
    {
        try
        {
            counter = 1;

            String builtString = "";
            int boxCounter = 1;
            int boxCounterCap = 30;
            Scanner fileReader = new Scanner(output);
            System.out.println("File opened Successfully.");

            File output = new File("PokemonHomeListV5.csv");
            Path test = Paths.get(output.getPath());

            Files.writeString(test, boxCounter + "-" + boxCounterCap + "\n", StandardOpenOption.CREATE_NEW);
            boxCounter = boxCounter + 30;
            boxCounterCap = boxCounterCap + 30;

            for(int i = 0; i < 6 && fileReader.hasNextLine(); i++)
            {
                builtString = (builtString + fileReader.nextLine() + ", =Sheet2!B" + counter + ", ");
                counter++;
            }
    
            Files.writeString(test, builtString + "\n", StandardOpenOption.APPEND);

            for(int i = 0; i < 4; i++)
            {
                builtString = "";

                for(int j = 0; j < 6; j++)
                {
                    builtString = builtString + fileReader.nextLine() + ", =Sheet2!B" + counter + ", ";
                    counter++;
                }

                Files.writeString(test, builtString + "\n", StandardOpenOption.APPEND);
            }

            Files.writeString(test, "\n", StandardOpenOption.APPEND);

            boolean lineFlag = false;

            while(lineFlag == false)
            {
                Files.writeString(test, boxCounter + "-" + boxCounterCap + "\n", StandardOpenOption.APPEND);
                boxCounter = boxCounter + 30;
                boxCounterCap = boxCounterCap + 30;

                for(int i = 0; i < 5; i++)
                {
                    builtString = "";

                    for(int j = 0; j < 6; j++)
                    {
                        builtString = builtString + fileReader.nextLine() + ", =Sheet2!B" + counter + ", ";
                        counter++;

                        if(fileReader.hasNextLine() == false)
                        {
                            Files.writeString(test, builtString + "\n", StandardOpenOption.APPEND);
                            lineFlag = true;
                        }
                    }

                    Files.writeString(test, builtString + "\n", StandardOpenOption.APPEND);

                    if(fileReader.hasNextLine() == false)
                    {
                        lineFlag = true;
                    }
                }

                Files.writeString(test, "\n", StandardOpenOption.APPEND);
            }

            fileReader.close();
        }
        catch(IOException e)
        {
            System.out.println("There was an error opening the CSV file.");
            System.exit(0);
        }
        catch(NoSuchElementException e)
        {
            System.out.println("CSV File Ended");
        }
    }

    public static void setGenders()
    {
        data[3].setHasGender(true);
        data[12].setHasGender(true);
        data[19].setHasGender(true);
        data[20].setHasGender(true);
        data[25].setHasGender(true);
        data[26].setHasGender(true);
        data[41].setHasGender(true);
        data[42].setHasGender(true);
        data[44].setHasGender(true);
        data[45].setHasGender(true);
        data[64].setHasGender(true);
        data[65].setHasGender(true);
        data[84].setHasGender(true);
        data[85].setHasGender(true);
        data[97].setHasGender(true);
        data[111].setHasGender(true);
        data[112].setHasGender(true);
        data[118].setHasGender(true);
        data[119].setHasGender(true);
        data[123].setHasGender(true);
        data[129].setHasGender(true);
        data[130].setHasGender(true);
        data[133].setHasGender(true);
        data[154].setHasGender(true);
        data[165].setHasGender(true);
        data[166].setHasGender(true);
        data[178].setHasGender(true);
        data[185].setHasGender(true);
        data[186].setHasGender(true);
        data[190].setHasGender(true);
        data[194].setHasGender(true);
        data[195].setHasGender(true);
        data[198].setHasGender(true);
        data[202].setHasGender(true);
        data[203].setHasGender(true);
        data[207].setHasGender(true);
        data[208].setHasGender(true);
        data[212].setHasGender(true);
        data[214].setHasGender(true);
        data[215].setHasGender(true);
        data[217].setHasGender(true);
        data[221].setHasGender(true);
        data[224].setHasGender(true);
        data[229].setHasGender(true);
        data[232].setHasGender(true);
        data[255].setHasGender(true);
        data[256].setHasGender(true);
        data[257].setHasGender(true);
        data[267].setHasGender(true);
        data[269].setHasGender(true);
        data[272].setHasGender(true);
        data[274].setHasGender(true);
        data[275].setHasGender(true);
        data[307].setHasGender(true);
        data[308].setHasGender(true);
        data[315].setHasGender(true);
        data[316].setHasGender(true);
        data[317].setHasGender(true);
        data[322].setHasGender(true);
        data[323].setHasGender(true);
        data[332].setHasGender(true);
        data[350].setHasGender(true);
        data[369].setHasGender(true);
        data[396].setHasGender(true);
        data[397].setHasGender(true);
        data[398].setHasGender(true);
        data[399].setHasGender(true);
        data[400].setHasGender(true);
        data[401].setHasGender(true);
        data[402].setHasGender(true);
        data[403].setHasGender(true);
        data[404].setHasGender(true);
        data[405].setHasGender(true);
        data[407].setHasGender(true);
        data[415].setHasGender(true);
        data[417].setHasGender(true);
        data[418].setHasGender(true);
        data[419].setHasGender(true);
        data[424].setHasGender(true);
        data[443].setHasGender(true);
        data[444].setHasGender(true);
        data[445].setHasGender(true);
        data[449].setHasGender(true);
        data[450].setHasGender(true);
        data[453].setHasGender(true);
        data[454].setHasGender(true);
        data[456].setHasGender(true);
        data[457].setHasGender(true);
        data[459].setHasGender(true);
        data[460].setHasGender(true);
        data[461].setHasGender(true);
        data[464].setHasGender(true);
        data[465].setHasGender(true);
        data[473].setHasGender(true);
        data[521].setHasGender(true);
        data[592].setHasGender(true);
        data[593].setHasGender(true);
        data[668].setHasGender(true);
        data[678].setHasGender(true);
        data[876].setHasGender(true);
        data[902].setHasGender(true);
        //data[903].setHasGender(true);

        data[916].setHasGender(true);
    }

    public static void setForms()
    {
        data[25].setHasAltForms(true);
        data[25].setAltForms(8);
        data[25].addFormName("Original Cap ");
        data[25].addFormName("Hoenn Cap ");
        data[25].addFormName("Sinnoh Cap ");
        data[25].addFormName("Unova Cap ");
        data[25].addFormName("Kalos Cap ");
        data[25].addFormName("Alola Cap ");
        data[25].addFormName("Partner Cap ");
        data[25].addFormName("World Cap ");

        data[201].setHasAltForms(true);
        data[201].setAltForms(27);
        data[201].addFormName(" A");
        data[201].addFormName(" B");
        data[201].addFormName(" C");
        data[201].addFormName(" D");
        data[201].addFormName(" E");
        data[201].addFormName(" F");
        data[201].addFormName(" G");
        data[201].addFormName(" H");
        data[201].addFormName(" I");
        data[201].addFormName(" J");
        data[201].addFormName(" K");
        data[201].addFormName(" L");
        data[201].addFormName(" M");
        data[201].addFormName(" N");
        data[201].addFormName(" O");
        data[201].addFormName(" P");
        data[201].addFormName(" Q");
        data[201].addFormName(" R");
        data[201].addFormName(" S");
        data[201].addFormName(" T");
        data[201].addFormName(" U");
        data[201].addFormName(" V");
        data[201].addFormName(" W");
        data[201].addFormName(" X");
        data[201].addFormName(" Y");
        data[201].addFormName(" Z");
        data[201].addFormName(" !");
        data[201].addFormName(" ?");

        data[412].setHasAltForms(true);
        data[412].setAltForms(2);
        data[412].addFormName("Plant ");
        data[412].addFormName("Sandy ");
        data[412].addFormName("Trash ");

        data[413].setHasAltForms(true);
        data[413].setAltForms(2);
        data[413].addFormName("Plant ");
        data[413].addFormName("Sandy ");
        data[413].addFormName("Trash ");

        data[422].setHasAltForms(true);
        data[422].setAltForms(1);
        data[422].addFormName("West Sea ");
        data[422].addFormName("East Sea ");

        data[423].setHasAltForms(true);
        data[423].setAltForms(1);
        data[423].addFormName("West Sea ");
        data[423].addFormName("East Sea ");

        data[479].setHasAltForms(true);
        data[479].setAltForms(5);
        data[479].addFormName(" Heat");
        data[479].addFormName(" Wash");
        data[479].addFormName(" Frost");
        data[479].addFormName(" Fan");
        data[479].addFormName(" Mow");

        data[492].setHasAltForms(true);
        data[492].setAltForms(1);
        data[492].addFormName("Land ");
        data[492].addFormName("Sky ");

        data[550].setHasAltForms(true);
        data[550].setAltForms(1);
        data[550].addFormName("Red Stripe ");
        data[550].addFormName("Blue Stripe ");

        data[585].setHasAltForms(true);
        data[585].setAltForms(3);
        data[585].addFormName("Spring ");
        data[585].addFormName("Summer ");
        data[585].addFormName("Autumn ");
        data[585].addFormName("Winter ");

        data[586].setHasAltForms(true);
        data[586].setAltForms(3);
        data[586].addFormName("Spring ");
        data[586].addFormName("Summer ");
        data[586].addFormName("Autumn ");
        data[586].addFormName("Winter ");

        data[641].setHasAltForms(true);
        data[641].setAltForms(1);
        data[641].addFormName(" Incarnate");
        data[641].addFormName(" Therian");

        data[642].setHasAltForms(true);
        data[642].setAltForms(1);
        data[642].addFormName(" Incarnate");
        data[642].addFormName(" Therian");

        data[645].setHasAltForms(true);
        data[645].setAltForms(1);
        data[645].addFormName(" Incarnate");
        data[645].addFormName(" Therian");

        data[666].setHasAltForms(true);
        data[666].setAltForms(19);
        data[666].addFormName("Icy Snow ");
        data[666].addFormName("Polar ");
        data[666].addFormName("Tundra ");
        data[666].addFormName("Continental ");
        data[666].addFormName("Garden ");
        data[666].addFormName("Elegant ");
        data[666].addFormName("Meadow ");
        data[666].addFormName("Modern ");
        data[666].addFormName("Marine ");
        data[666].addFormName("Archipelago ");
        data[666].addFormName("High Plains ");
        data[666].addFormName("Sandstorm ");
        data[666].addFormName("River ");
        data[666].addFormName("Monsoon ");
        data[666].addFormName("Savanna ");
        data[666].addFormName("Sun ");
        data[666].addFormName("Ocean ");
        data[666].addFormName("Jungle ");
        data[666].addFormName("Fancy ");
        data[666].addFormName("Pokeball ");

        data[669].setHasAltForms(true);
        data[669].setAltForms(4);
        data[669].addFormName("Red Flower ");
        data[669].addFormName("Yellow Flower ");
        data[669].addFormName("Orange Flower ");
        data[669].addFormName("Blue Flower ");
        data[669].addFormName("White Flower ");

        data[670].setHasAltForms(true);
        data[670].setAltForms(4);
        data[670].addFormName("Red Flower ");
        data[670].addFormName("Yellow Flower ");
        data[670].addFormName("Orange Flower ");
        data[670].addFormName("Blue Flower ");
        data[670].addFormName("White Flower ");

        data[671].setHasAltForms(true);
        data[671].setAltForms(4);
        data[671].addFormName("Red Flower ");
        data[671].addFormName("Yellow Flower ");
        data[671].addFormName("Orange Flower ");
        data[671].addFormName("Blue Flower ");
        data[671].addFormName("White Flower ");

        data[676].setHasAltForms(true);
        data[676].setAltForms(9);
        data[676].addFormName("Natural Trim ");
        data[676].addFormName("Heart Trim ");
        data[676].addFormName("Star Trim ");
        data[676].addFormName("Diamond Trim ");
        data[676].addFormName("Debutante Trim ");
        data[676].addFormName("Matron Trim ");
        data[676].addFormName("Dandy Trim ");
        data[676].addFormName("La Reine Trim ");
        data[676].addFormName("Kabuki Trim ");
        data[676].addFormName("Pharaoh Trim ");

        data[710].setHasAltForms(true);
        data[710].setAltForms(3);
        data[710].addFormName("Small Size ");
        data[710].addFormName("Average Size ");
        data[710].addFormName("Large Size ");
        data[710].addFormName("Super Size ");

        data[711].setHasAltForms(true);
        data[711].setAltForms(3);
        data[711].addFormName("Small Size ");
        data[711].addFormName("Average Size ");
        data[711].addFormName("Large Size ");
        data[711].addFormName("Super Size ");

        data[741].setHasAltForms(true);
        data[741].setAltForms(3);
        data[741].addFormName("Baile ");
        data[741].addFormName("Pom-Pom ");
        data[741].addFormName("Pa-u ");
        data[741].addFormName("Sensu ");

        data[745].setHasAltForms(true);
        data[745].setAltForms(2);
        data[745].addFormName("Midday ");
        data[745].addFormName("Midnight ");
        data[745].addFormName("Dusk ");

        data[774].setHasAltForms(true);
        data[774].setAltForms(7);
        data[774].addFormName("Red Core ");
        data[774].addFormName("Orange Core ");
        data[774].addFormName("Yellow Core ");
        data[774].addFormName("Green Core ");
        data[774].addFormName("Blue Core ");
        data[774].addFormName("Indigo Core ");
        data[774].addFormName("Violet Core ");

        data[801].setHasAltForms(true);
        data[801].setAltForms(1);
        data[801].addFormName("Original Color ");

        data[849].setHasAltForms(true);
        data[849].setAltForms(1);
        data[849].addFormName("Amped ");
        data[849].addFormName("Low Key ");

        data[854].setHasAltForms(true);
        data[854].setAltForms(1);
        data[854].addFormName("Phony ");
        data[854].addFormName("Antique ");

        data[855].setHasAltForms(true);
        data[855].setAltForms(1);
        data[855].addFormName("Phony ");
        data[855].addFormName("Antique ");

        data[869].setHasAltForms(true);
        data[869].setAltForms(9);
        data[869].addFormName("Vanilla Cream ");
        data[869].addFormName("Ruby Cream ");
        data[869].addFormName("Matcha Cream ");
        data[869].addFormName("Mint Cream ");
        data[869].addFormName("Lemon Cream ");
        data[869].addFormName("Salted Cream ");
        data[869].addFormName("Ruby Swirl ");
        data[869].addFormName("Caramel Swirl ");
        data[869].addFormName("Rainbow Swirl ");
        data[869].addFormName("Strawberry ");
        data[869].addFormName("Berry ");
        data[869].addFormName("Love ");
        data[869].addFormName("Star ");
        data[869].addFormName("Clover ");
        data[869].addFormName("Flower ");
        data[869].addFormName("Ribbon ");

        data[892].setHasAltForms(true);
        data[892].setAltForms(1);
        data[892].addFormName("Single Strike ");
        data[892].addFormName("Rapid Strike ");

        data[893].setHasAltForms(true);
        data[893].setAltForms(1);
        data[893].addFormName("Dada ");

        data[905].setHasAltForms(true);
        data[905].setAltForms(1);
        data[905].addFormName(" Incarnate");
        data[905].addFormName(" Therian");

        data[982].setHasAltForms(true);
        data[982].setAltForms(1);
        data[982].addFormName("Two Segment ");
        data[982].addFormName("Three Segment ");

        data[925].setHasAltForms(true);
        data[925].setAltForms(1);
        data[925].addFormName(" Family of Three");
        data[925].addFormName(" Family of Four");

        data[978].setHasAltForms(true);
        data[978].setAltForms(2);
        data[978].addFormName("Curly ");
        data[978].addFormName("Droopy ");
        data[978].addFormName("Stretchy ");

        data[931].setHasAltForms(true);
        data[931].setAltForms(3);
        data[931].addFormName("Green ");
        data[931].addFormName("Blue ");
        data[931].addFormName("White ");
        data[931].addFormName("Yellow ");

        data[999].setHasAltForms(true);
        data[999].setAltForms(1);
        data[999].addFormName("Chest ");
        data[999].addFormName("Roaming ");

        //Teal Mask
        data[901].setHasAltForms(true);
        data[901].setAltForms(1);
        data[901].addFormName("Bloodmoon ");

        data[1012].setHasAltForms(true);
        data[1012].setAltForms(1);
        data[1012].addFormName("Counterfeit ");
        data[1012].addFormName("Artisan ");

        data[1013].setHasAltForms(true);
        data[1013].setAltForms(1);
        data[1013].addFormName("Counterfeit ");
        data[1013].addFormName("Artisan ");
    }

    public static void setRegionalForms()
    {
        data[19].setHasAlolanForm(true);
        data[20].setHasAlolanForm(true);
        data[26].setHasAlolanForm(true);
        data[27].setHasAlolanForm(true);
        data[28].setHasAlolanForm(true);
        data[37].setHasAlolanForm(true);
        data[38].setHasAlolanForm(true);
        data[50].setHasAlolanForm(true);
        data[51].setHasAlolanForm(true);
        data[52].setHasAlolanForm(true);
        data[53].setHasAlolanForm(true);
        data[74].setHasAlolanForm(true);
        data[75].setHasAlolanForm(true);
        data[76].setHasAlolanForm(true);
        data[88].setHasAlolanForm(true);
        data[89].setHasAlolanForm(true);
        data[103].setHasAlolanForm(true);
        data[105].setHasAlolanForm(true);

        data[52].setHasGalarianForm(true);
        data[77].setHasGalarianForm(true);
        data[78].setHasGalarianForm(true);
        data[79].setHasGalarianForm(true);
        data[80].setHasGalarianForm(true);
        data[83].setHasGalarianForm(true);
        data[110].setHasGalarianForm(true);
        data[122].setHasGalarianForm(true);
        data[144].setHasGalarianForm(true);
        data[145].setHasGalarianForm(true);
        data[146].setHasGalarianForm(true);
        data[199].setHasGalarianForm(true);
        data[222].setHasGalarianForm(true);
        data[263].setHasGalarianForm(true);
        data[264].setHasGalarianForm(true);
        data[554].setHasGalarianForm(true);
        data[555].setHasGalarianForm(true);
        data[562].setHasGalarianForm(true);
        data[618].setHasGalarianForm(true);

        data[58].setHasHisuianForm(true);
        data[59].setHasHisuianForm(true);
        data[100].setHasHisuianForm(true);
        data[101].setHasHisuianForm(true);
        data[157].setHasHisuianForm(true);
        data[211].setHasHisuianForm(true);
        data[215].setHasHisuianForm(true);
        data[503].setHasHisuianForm(true);
        data[549].setHasHisuianForm(true);
        data[550].setHasHisuianForm(true);
        data[570].setHasHisuianForm(true);
        data[571].setHasHisuianForm(true);
        data[628].setHasHisuianForm(true);
        data[713].setHasHisuianForm(true);
        data[705].setHasHisuianForm(true);
        data[706].setHasHisuianForm(true);
        data[724].setHasHisuianForm(true);

        data[128].setHasPaldeanForm(true);
        data[194].setHasPaldeanForm(true);
    }

    public static void setGmaxForms()
    {
        data[3].setHasGmaxForm(true);
        data[6].setHasGmaxForm(true);
        data[9].setHasGmaxForm(true);
        data[12].setHasGmaxForm(true);
        data[25].setHasGmaxForm(true);
        data[52].setHasGmaxForm(true);
        data[68].setHasGmaxForm(true);
        data[94].setHasGmaxForm(true);
        data[99].setHasGmaxForm(true);
        data[131].setHasGmaxForm(true);
        data[133].setHasGmaxForm(true);
        data[143].setHasGmaxForm(true);
        data[569].setHasGmaxForm(true);
        data[809].setHasGmaxForm(true);
        data[812].setHasGmaxForm(true);
        data[815].setHasGmaxForm(true);
        data[818].setHasGmaxForm(true);
        data[823].setHasGmaxForm(true);
        data[826].setHasGmaxForm(true);
        data[834].setHasGmaxForm(true);
        data[839].setHasGmaxForm(true);
        data[841].setHasGmaxForm(true);
        data[842].setHasGmaxForm(true);
        data[844].setHasGmaxForm(true);
        data[849].setHasGmaxForm(true);
        data[851].setHasGmaxForm(true);
        data[858].setHasGmaxForm(true);
        data[861].setHasGmaxForm(true);
        data[869].setHasGmaxForm(true);
        data[879].setHasGmaxForm(true);
        data[884].setHasGmaxForm(true);
        data[892].setHasGmaxForm(true);
        data[892].setGmaxForms(1);
    }
}