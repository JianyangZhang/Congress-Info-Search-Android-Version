package android.congressinfo.LegislatorsJSON;

/**
 * Created by jiany on 2016/11/19.
 */

public class LegislatorsPage
{
    private int per_page;

    private int count;

    private int page;

    public int getPer_page ()
    {
        return per_page;
    }

    public void setPer_page (int per_page)
    {
        this.per_page = per_page;
    }

    public int getCount ()
    {
        return count;
    }

    public void setCount (int count)
    {
        this.count = count;
    }

    public int getPage ()
    {
        return page;
    }

    public void setPage (int page)
    {
        this.page = page;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [per_page = "+per_page+", count = "+count+", page = "+page+"]";
    }
}
