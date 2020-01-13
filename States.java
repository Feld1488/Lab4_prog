public enum States {
    FIND_SMB("Обнаружил"),
    DONTFRIGHT("Не испугался"),
    KNOW("Знает, что "),
    WANTTOLOOK("Хочет оглядется"),
    DEFAULT("Пишет ПСЖ");

    private String title;

    States(String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }
}
