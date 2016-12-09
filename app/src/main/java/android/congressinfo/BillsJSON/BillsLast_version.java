package android.congressinfo.BillsJSON;

public class BillsLast_version
{
    private String version_name;

    private BillsUrls urls;

    private String bill_version_id;

    private String pages;

    private String issued_on;

    private String version_code;

    public String getVersion_name ()
    {
        return version_name;
    }

    public void setVersion_name (String version_name)
    {
        this.version_name = version_name;
    }

    public BillsUrls getUrls ()
    {
        return urls;
    }

    public void setUrls (BillsUrls urls)
    {
        this.urls = urls;
    }

    public String getBill_version_id ()
    {
        return bill_version_id;
    }

    public void setBill_version_id (String bill_version_id)
    {
        this.bill_version_id = bill_version_id;
    }

    public String getPages ()
    {
        return pages;
    }

    public void setPages (String pages)
    {
        this.pages = pages;
    }

    public String getIssued_on ()
    {
        return issued_on;
    }

    public void setIssued_on (String issued_on)
    {
        this.issued_on = issued_on;
    }

    public String getVersion_code ()
    {
        return version_code;
    }

    public void setVersion_code (String version_code)
    {
        this.version_code = version_code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [version_name = "+version_name+", urls = "+urls+", bill_version_id = "+bill_version_id+", pages = "+pages+", issued_on = "+issued_on+", version_code = "+version_code+"]";
    }
}

