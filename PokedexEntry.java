import java.util.ArrayList;

public class PokedexEntry
{
    private String name;
    private boolean hasGender;
    private boolean hasAltForms;
    private int altForms;
    private boolean hasAlolanForm;
    private boolean hasGalarianForm;
    private boolean hasHisuianForm;
    private boolean hasGmaxForm;
    private int gmaxForms;
    private boolean hasPaldeanForm;
    private ArrayList<String> formNames;

    public PokedexEntry()
    {
        setName("Blank Name");
        setHasGender(false);
        setHasAltForms(false);
        setAltForms(0);
        setHasAlolanForm(false);
        setHasGalarianForm(false);
        setHasHisuianForm(false);
        setHasGmaxForm(false);
        setGmaxForms(0);
        setHasPaldeanForm(false);
        formNames = new ArrayList<String>();
    }

    public String getName() 
    {
        return this.name;
    }

    public void setName(String nName) 
    {
        this.name = nName;
    }

    public boolean getHasGender() 
    {
        return this.hasGender;
    }

    public void setHasGender(boolean nHasGender) 
    {
        this.hasGender = nHasGender;
    }

    public boolean getHasAltForms() 
    {
        return this.hasAltForms;
    }

    public void setHasAltForms(boolean nHasAltForms) 
    {
        this.hasAltForms = nHasAltForms;
    }

    public int getAltForms() 
    {
        return this.altForms;
    }

    public void setAltForms(int nAltForms) 
    {
        this.altForms = nAltForms;
    }

    public void addFormName(String nName) 
    {
        formNames.add(nName);
    }

    public String getFormName(int index) 
    {
        return formNames.get(index);
    }

    public boolean getHasAlolanForm() 
    {
        return this.hasAlolanForm;
    }

    public void setHasAlolanForm(boolean nHasAlolanForm) 
    {
        this.hasAlolanForm = nHasAlolanForm;
    }

    public boolean getHasGalarianForm() 
    {
        return this.hasGalarianForm;
    }

    public void setHasGalarianForm(boolean nHasGalarianForm) 
    {
        this.hasGalarianForm = nHasGalarianForm;
    }

    public boolean getHasHisuianForm() 
    {
        return this.hasHisuianForm;
    }

    public void setHasHisuianForm(boolean nHasHisuianForm) 
    {
        this.hasHisuianForm = nHasHisuianForm;
    }

    public boolean getHasGmaxForm() 
    {
        return this.hasGmaxForm;
    }

    public void setHasGmaxForm(boolean nHasGmaxForm) 
    {
        this.hasGmaxForm = nHasGmaxForm;
    }

    public int getGmaxForms() 
    {
        return this.gmaxForms;
    }

    public void setGmaxForms(int nGmaxForms) 
    {
        this.gmaxForms = nGmaxForms;
    }

    public boolean getHasPaldeanForm() 
    {
        return this.hasPaldeanForm;
    }

    public void setHasPaldeanForm(boolean nHasPaldeanForm) 
    {
        this.hasPaldeanForm = nHasPaldeanForm;
    }
}