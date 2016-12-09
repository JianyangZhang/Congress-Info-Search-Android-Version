package android.congressinfo.BillsJSON;

public class BillsSponsor
{
    private String name_suffix;

    private String first_name;

    private String title;

    private String nickname;

    private String middle_name;

    private String last_name;

    public String getName_suffix ()
{
    return name_suffix;
}

    public void setName_suffix (String name_suffix)
    {
        this.name_suffix = name_suffix;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getNickname ()
{
    return nickname;
}

    public void setNickname (String nickname)
    {
        this.nickname = nickname;
    }

    public String getMiddle_name ()
{
    return middle_name;
}

    public void setMiddle_name (String middle_name)
    {
        this.middle_name = middle_name;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name_suffix = "+name_suffix+", first_name = "+first_name+", title = "+title+", nickname = "+nickname+", middle_name = "+middle_name+", last_name = "+last_name+"]";
    }
}


