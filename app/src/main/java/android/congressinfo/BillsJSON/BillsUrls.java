package android.congressinfo.BillsJSON;

public class BillsUrls
{
    private String opencongress;

    private String govtrack;

    private String congress;

    private String pdf;

    public String getPDF ()
    {
        return pdf;
    }

    public void setPDF (String pdf)
    {
        this.pdf = pdf;
    }

    public String getOpencongress ()
    {
        return opencongress;
    }

    public void setOpencongress (String opencongress)
    {
        this.opencongress = opencongress;
    }

    public String getGovtrack ()
    {
        return govtrack;
    }

    public void setGovtrack (String govtrack)
    {
        this.govtrack = govtrack;
    }

    public String getCongress ()
    {
        return congress;
    }

    public void setCongress (String congress)
    {
        this.congress = congress;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [opencongress = "+opencongress+", govtrack = "+govtrack+", congress = "+congress+"]";
    }
}

