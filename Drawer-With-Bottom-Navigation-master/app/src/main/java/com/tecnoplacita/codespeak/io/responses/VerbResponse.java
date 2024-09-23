package com.tecnoplacita.codespeak.io.responses;


import java.util.List;

public class VerbResponse {
    private int id;
    private String baseForm;
    private String translation;
    private String thirdPerson;
    private String past;
    private String pastParticiple;
    private String gerund;
    private List<Sentence> sentences;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaseForm() {
        return baseForm;
    }

    public void setBaseForm(String baseForm) {
        this.baseForm = baseForm;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getThirdPerson() {
        return thirdPerson;
    }

    public void setThirdPerson(String thirdPerson) {
        this.thirdPerson = thirdPerson;
    }

    public String getPast() {
        return past;
    }

    public void setPast(String past) {
        this.past = past;
    }

    public String getPastParticiple() {
        return pastParticiple;
    }

    public void setPastParticiple(String pastParticiple) {
        this.pastParticiple = pastParticiple;
    }

    public String getGerund() {
        return gerund;
    }

    public void setGerund(String gerund) {
        this.gerund = gerund;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }
// Getters y Setters

    public static class Sentence {
        private int id;
        private String tense;
        private String type;
        private String content;
        private String figurativePronunciation;
        private String translation;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFigurativePronunciation() {
            return figurativePronunciation;
        }

        public void setFigurativePronunciation(String figurativePronunciation) {
            this.figurativePronunciation = figurativePronunciation;
        }

        public String getTranslation() {
            return translation;
        }

        public void setTranslation(String translation) {
            this.translation = translation;
        }

// Getters y Setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTense() {
            return tense;
        }

        public void setTense(String tense) {
            this.tense = tense;
        }


    }

    // Getters y Setters para las propiedades principales...
}
