
    create sequence PBM_PROCEDIMENT_SEQ start with 1 increment by  1;
    create sequence PBM_UNITATORGANICA_SEQ start with 1 increment by  1;

    create table PBM_PROCEDIMENT (
        ID number(19,0) not null,
        CODISIA varchar2(8 char) not null,
        NOM varchar2(50 char) not null,
        UNITATORGANICAID number(19,0) not null
    );

    create table PBM_UNITATORGANICA (
        ID number(19,0) not null,
        CODIDIR3 varchar2(9 char) not null,
        DATACREACIO date not null,
        ESTAT number(10,0) not null,
        NOM varchar2(50 char) not null
    );

    create index PBM_PROCEDIMENT_PK_I on PBM_PROCEDIMENT (ID);
    create index PBM_PROCEDIMENT_CODISIA_UK_I on PBM_PROCEDIMENT (CODISIA);
    create index PBM_PROCEDIMENT_UNITAT_FK_I on PBM_PROCEDIMENT (UNITATORGANICAID);

    alter table PBM_PROCEDIMENT
        add constraint PBM_PROCEDIMENT_PK primary key (ID);

    alter table PBM_PROCEDIMENT
        add constraint PBM_PROCEDIMENT_CODISIA_UK unique (CODISIA);

    create index PBM_UNITAT_PK_I on PBM_UNITATORGANICA (ID);
    create index PBM_UNITAT_CODIDIR3_UK_I on PBM_UNITATORGANICA (CODIDIR3);

    alter table PBM_UNITATORGANICA
        add constraint PBM_UNITAT_PK primary key (ID);

    alter table PBM_UNITATORGANICA
        add constraint PBM_UNITAT_CODIDIR3_UK unique (CODIDIR3);

    alter table PBM_PROCEDIMENT
        add constraint PBM_PROCEDIMENT_UNITAT_FK
        foreign key (UNITATORGANICAID)
        references PBM_UNITATORGANICA;

    -- Grants per l'usuari www_pinbalmonitor
    -- seqüències
    GRANT SELECT, ALTER ON PBM_PROCEDIMENT_SEQ TO WWW_PINBALMONITOR;
    GRANT SELECT, ALTER ON PBM_UNITATORGANICA_SEQ TO WWW_PINBALMONITOR;
    -- taules
    GRANT SELECT, INSERT, UPDATE, DELETE ON PBM_PROCEDIMENT TO WWW_PINBALMONITOR;
    GRANT SELECT, INSERT, UPDATE, DELETE ON PBM_UNITATORGANICA TO WWW_PINBALMONITOR;


