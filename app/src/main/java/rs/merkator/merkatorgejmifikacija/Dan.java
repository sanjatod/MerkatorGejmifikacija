package rs.merkator.merkatorgejmifikacija;

public class Dan {
    private int rowid;
    private int RB;
    private String TekstPitanja;
    private String Slika;
    private int TipOdgovora;
    private String TacanOdgovor;
    private String Odgovori;
    private int Odgovoreno;
    private int segment;
    private String tekstIspod;

    public Dan(int rowid, int RB, String tekstPitanja, String slika, int tipOdgovora,
               String tacanOdgovor, String odgovori, int odgovoreno, int segment, String tekstIspod) {
        this.rowid=rowid;
        this.RB = RB;
        TekstPitanja = tekstPitanja;
        Slika = slika;
        TipOdgovora = tipOdgovora;
        TacanOdgovor = tacanOdgovor;
        Odgovori = odgovori;
        Odgovoreno=odgovoreno;
        this.segment=segment;
        this.tekstIspod=tekstIspod;

    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public int getRB() {
        return RB;
    }

    public void setRB(int RB) {
        this.RB = RB;
    }

    public String getTekstPitanja() {
        return TekstPitanja;
    }

    public void setTekstPitanja(String tekstPitanja) {
        TekstPitanja = tekstPitanja;
    }

    public String getSlika() {
        return Slika;
    }

    public void setSlika(String slika) {
        Slika = slika;
    }

    public int getTipOdgovora() {
        return TipOdgovora;
    }

    public void setTipOdgovora(int tipOdgovora) {
        TipOdgovora = tipOdgovora;
    }

    public String getTacanOdgovor() {
        return TacanOdgovor;
    }

    public void setTacanOdgovor(String tacanOdgovor) {
        TacanOdgovor = tacanOdgovor;
    }

    public String getOdgovori() {
        return Odgovori;
    }

    public void setOdgovori(String odgovori) {
        Odgovori = odgovori;
    }

    public int getOdgovoreno() {
        return Odgovoreno;
    }

    public void setOdgovoreno(int odgovoreno) {
        Odgovoreno = odgovoreno;
    }

    public int getSegment() {
        return segment;
    }

    public void setSegment(int segment) {
        this.segment = segment;
    }

    public String getTekstIspod() {
        return tekstIspod;
    }

    public void setTekstIspod(String tekstIspod) {
        this.tekstIspod = tekstIspod;
    }
}

