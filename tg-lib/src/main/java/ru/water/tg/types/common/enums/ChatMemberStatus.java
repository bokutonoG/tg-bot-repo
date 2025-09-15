package ru.water.tg.types.common.enums;

public enum ChatMemberStatus {
    CREATOR("creator"),
    ADMINISTRATOR("administrator"),
    MEMBER("member"),
    RESTRICTED("restricted"),
    LEFT("left"),
    KICKED("kicked"),;


    public final String status;

    ChatMemberStatus(String status) {
        this.status = status;
    }
}
