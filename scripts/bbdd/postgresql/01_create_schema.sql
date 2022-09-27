
    create sequence PBM_PROCEDIMENT_SEQ start 1 increment 1;
    create sequence PBM_UNITATORGANICA_SEQ start 1 increment 1;

    create table PBM_PROCEDIMENT (
       ID int8 not null,
        CODISIA varchar(8) not null,
        NOM varchar(50) not null,
        UNITATORGANICAID int8 not null,
        constraint PBM_PROCEDIMENT_PK primary key (ID)
    );

    create table PBM_UNITATORGANICA (
       ID int8 not null,
        CODIDIR3 varchar(9) not null,
        DATACREACIO date not null,
        ESTAT int4 not null,
        NOM varchar(50) not null,
        constraint PBM_UNITAT_PK primary key (ID)
    );

    create index PBM_PROCEDIMENT_PK_I on PBM_PROCEDIMENT (ID);
    create index PBM_PROCEDIMENT_CODISIA_UK_I on PBM_PROCEDIMENT (CODISIA);
    create index PBM_PROCEDIMENT_UNITAT_FK_I on PBM_PROCEDIMENT (UNITATORGANICAID);

    alter table PBM_PROCEDIMENT 
       add constraint PBM_PROCEDIMENT_CODISIA_UK unique (CODISIA);

    create index PBM_UNITAT_PK_I on PBM_UNITATORGANICA (ID);
    create index PBM_UNITAT_CODIDIR3_UK_I on PBM_UNITATORGANICA (CODIDIR3);

    alter table PBM_UNITATORGANICA 
       add constraint PBM_UNITAT_CODIDIR3_UK unique (CODIDIR3);

    alter table PBM_PROCEDIMENT 
       add constraint PBM_PROCEDIMENT_UNITAT_FK 
       foreign key (UNITATORGANICAID) 
       references PBM_UNITATORGANICA;
