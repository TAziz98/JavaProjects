import java.util.Locale;


public enum Nationality {Polish, Ukrainian, Belarussian, Slovak, Lithuanian, Latvian, British, Indian, Chinese, Vietnamese;
    private Locale locale;
    
    private Nationality(){
        locale = new Locale(this.toString());
    }

    public Locale getLocale() {
        return locale;
    }
    
    
}

