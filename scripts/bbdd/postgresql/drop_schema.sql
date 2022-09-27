
    alter table PBM_PROCEDIMENT 
       drop constraint PBM_PROCEDIMENT_UNITAT_FK;

    drop table if exists PBM_PROCEDIMENT cascade;

    drop table if exists PBM_UNITATORGANICA cascade;

    drop sequence if exists PBM_PROCEDIMENT_SEQ;

    drop sequence if exists PBM_UNITATORGANICA_SEQ;
