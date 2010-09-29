	--drop table "APP"."AUDITORIA";
    create table "APP"."AUDITORIA"(
        "AUD_ID" BIGINT not null generated always as identity,
       "AUD_ACCION" VARCHAR(200) not null,
       "AUD_OBJETO" VARCHAR(200),
       "AUD_FK" VARCHAR(100),
       "AUD_FECHA" TIMESTAMP not null,
       "AUD_USUARIO" VARCHAR(200) not null,
       "AUD_ESTACION" VARCHAR(200) not null,
        constraint "SQL100216215716790" primary key ("AUD_ID")
    );
    create unique index "SQL100626213637630" on "APP"."AUDITORIA"("AUD_ID");


	--DROP table "APP"."DOMINIO";
    create table "APP"."DOMINIO"(
        "DOM_ID" BIGINT not null generated always as identity,
       "DOM_TIPO" VARCHAR(200),
       "DOM_CLAVE" VARCHAR(200),
       "DOM_CODIGO" VARCHAR(200),
       "DOM_VALOR_MOSTRAR" VARCHAR(200),
       "DOM_DESCRIPCION" VARCHAR(200),
        constraint "SQL100326234107160" primary key ("DOM_ID")
    );

    create unique index "SQL100605120001810" on "APP"."DOMINIO"("DOM_ID");
    
	--DROP table "APP"."EXAMEN";
    create table "APP"."EXAMEN"(
       "EXA_ID" BIGINT not null generated always as identity,
       "EXA_CODIGO" VARCHAR(20) not null unique,
       "EXA_NOMBRE" VARCHAR(100) not null,
        constraint "SQL100326234105961" primary key ("EXA_ID")
    );

    create unique index "SQL100326234105960" on "APP"."EXAMEN"("EXA_CODIGO");
    create unique index "SQL100326234105961" on "APP"."EXAMEN"("EXA_ID");
    
    
	--DROP table "APP"."EXAMEN_DETALLE";
    create table "APP"."EXAMEN_DETALLE"(
       "EXAD_ID" BIGINT not null generated always as identity,
       "EXAD_DETALLE" VARCHAR(200) not null,
       "EXAD_CODIGO" VARCHAR(200) not null unique,
       "EXA_ID" BIGINT not null,
       "EXAD_ORDEN" BIGINT not null,       
        constraint "SQL100327170410820" primary key ("EXAD_ID")
    );
	
	create unique index "SQL200326234105960" on "APP"."EXAMEN_DETALLE"("EXAD_CODIGO");
    create unique index "SQL100327170410820" on "APP"."EXAMEN_DETALLE"("EXAD_ID");
    create index "SQL100327170543150" on "APP"."EXAMEN_DETALLE"("EXA_ID");
    
	--drop table "APP"."PERMISO";
    create table "APP"."PERMISO"(
       "PER_ID" BIGINT not null generated always as identity,
       "PER_CODIGO" VARCHAR(20) not null unique,
       "PER_DESCRIPCION" VARCHAR(100) not null,
        constraint "SQL100326234106211" primary key ("PER_ID")
    );

    create unique index "SQL100326234106210" on "APP"."PERMISO"("PER_CODIGO");
    create unique index "SQL100326234106211" on "APP"."PERMISO"("PER_ID");
    
	--drop table "APP"."PERSONA";
     create table "APP"."PERSONA"(
        "PER_ID" BIGINT not null generated always as identity,
       "PER_APELLIDO" VARCHAR(60) not null,
       "PER_NOMBRE" VARCHAR(60) not null,
       "PER_TIPO_DOC" VARCHAR(30) not null,
       "PER_NUMERO_DOC" VARCHAR(30) not null,
       "PER_SEXO" VARCHAR(20) not null,
       "PER_FECHA_NACIMIENTO" TIMESTAMP,
       "PER_ESTADO_CIVIL" VARCHAR(20),
       "PER_GRUPO_SANGUINEO" VARCHAR(20),
       "PER_TELEFONO" VARCHAR(40),
       "PER_CELULAR" VARCHAR(40),
       "PER_CORREO" VARCHAR(60),
       "PER_ESTUDIOS" VARCHAR(200),
       "PER_DOMICILIO" VARCHAR(200),
       "PER_OBSERVACIONES" VARCHAR(200),
       "PER_FOTO" BLOB,
       "PER_FIRMA" BLOB,
       "PER_LOCALIDAD" VARCHAR(200),
       "PER_EDUCACION" VARCHAR(2),
       "PER_LICENCIA_DE_CONDUCIR" VARCHAR(2),
       "PER_TIEMPO_QUE_LLEVA_CONDUCIENDO" VARCHAR(2),
       "PER_TOMA_MEDICAMENTOS_REGULARMENTE_SN" VARCHAR(2),
       "PER_TOMO_HOY_PSICOFARMACOS" VARCHAR(2),
       "PER_TOMO_HOY_ALCOHOL" VARCHAR(2),
        constraint "SQL100326234107330" primary key ("PER_ID")
    );

    create unique index "SQL100326234107330" on "APP"."PERSONA"("PER_ID");

    
    
    
	--drop table "APP"."PERSONA_EXAMEN";
    create table "APP"."PERSONA_EXAMEN"(
       "PEXA_ID" BIGINT not null generated always as identity,
       "PER_ID" BIGINT not null,
       "EXA_ID" BIGINT not null,
       "PEXA_FECHA" TIMESTAMP not null,
       "PEXA_RESULTADO" VARCHAR(100),
       "PEXA_ESTADO" VARCHAR(100) not null,
       "PEXA_NOTA" NUMERIC(5,2),
       "PEXA_OBS" VARCHAR(200),
       "PEXA_ADJ" BLOB,
       "PEXA_RESULTADO_MEDICO" VARCHAR(100),
       "PEXA_NOMBRE_ADJUNTO" VARCHAR(200),
       "PEXA_TIPO_EXAMEN" VARCHAR(100),
        constraint "PEXA_ID" primary key ("PEXA_ID")
    );

    create unique index "SQL100414203810030" on "APP"."PERSONA_EXAMEN"("PEXA_ID");
    create index "SQL100414203901120" on "APP"."PERSONA_EXAMEN"("PER_ID");
    create index "SQL100414203901350" on "APP"."PERSONA_EXAMEN"("EXA_ID");
    
	--drop table "APP"."PERSONA_RESTRICION";
    create table "APP"."PERSONA_RESTRICION"(
        "ID" BIGINT not null generated always as identity,
       "PER_ID" BIGINT not null,
       "DOM_ID" BIGINT,
       "DESCRIPCION" VARCHAR(200) not null,
        constraint "SQL100326234107500" primary key ("ID")
    );


    create unique index "SQL100326234107500" on "APP"."PERSONA_RESTRICION"("ID");
    create index "SQL100326234108350" on "APP"."PERSONA_RESTRICION"("PER_ID");
    
	--drop table "APP"."PROPIEDAD";
    create table "APP"."PROPIEDAD"(
        "PROP_CLAVE" VARCHAR(200) not null,
       "PROP_VALOR" VARCHAR(200) not null,
       "PROP_BLOB" BLOB,
        constraint "SQL100307164231900" primary key ("PROP_CLAVE")
    );

    create unique index "SQL100326233952860" on "APP"."PROPIEDAD"("PROP_CLAVE");
    
    
    --drop table "APP"."RESULTADO";
    create table "APP"."RESULTADO"(
        "RES_ID" BIGINT not null generated always as identity,
       "RES_ETAPA" BIGINT,
       "RDE_ID" BIGINT not null,
       "RES_VALOR1" NUMERIC(14,3),
       "RES_VALOR2" NUMERIC(14,3),
       "RES_ETAPA_DESC" VARCHAR(100),
        constraint "SQL100414203629050" primary key ("RES_ID")
    );
    
    create unique index "SQL100414203629050" on "APP"."RESULTADO"("RES_ID");
    create index "SQL100414204123290" on "APP"."RESULTADO"("RDE_ID");
    
    --drop table "APP"."RESULTADO_DETALLE_EXAMEN";
    create table "APP"."RESULTADO_DETALLE_EXAMEN"(
        "RDE_ID" BIGINT not null generated always as identity,
       "EXAD_ID" BIGINT not null,
       "PEXA_ID" BIGINT not null,
       "RDE_RESULTADO" VARCHAR(100),
       "RDE_DETALLE_RESULTADO" VARCHAR(500),
       "RDE_NOTA" NUMERIC(14,3),
       "RDE_NOTA2" NUMERIC(14,3),
        constraint "SQL100414203432130" primary key ("RDE_ID")
    );
    
	create unique index "SQL100414203432130" on "APP"."RESULTADO_DETALLE_EXAMEN"("RDE_ID");
    create index "SQL100414204041030" on "APP"."RESULTADO_DETALLE_EXAMEN"("PEXA_ID");
    create index "SQL100414204041200" on "APP"."RESULTADO_DETALLE_EXAMEN"("EXAD_ID");
    
    --drop table "APP"."USUARIO";
    create table "APP"."USUARIO"(
        "USR_NOMBRE" VARCHAR(200) not null,
       "USR_CLAVE" VARCHAR(20) not null,
       "USR_ULTIMO_ACCESO" TIMESTAMP,
       "USR_HABILITADO_SN" VARCHAR(1) not null,
       "DELETED_SN" VARCHAR(1) not null,
        constraint "SQL100326234106450" primary key ("USR_NOMBRE")
    );

    create unique index "SQL100326234106450" on "APP"."USUARIO"("USR_NOMBRE");
    
    --drop table "APP"."USUARIO_PERMISO";
    create table "APP"."USUARIO_PERMISO"(
        "ID" BIGINT not null generated always as identity,
       "PER_ID" BIGINT not null,
       "USR_NOMBRE" VARCHAR(200) not null,
        constraint "SQL100326234106820" primary key ("ID")
    );

    
    create unique index "SQL100326234106820" on "APP"."USUARIO_PERMISO"("ID");
    create index "SQL100326234108110" on "APP"."USUARIO_PERMISO"("PER_ID");
    create index "SQL100326234108230" on "APP"."USUARIO_PERMISO"("USR_NOMBRE");
    
    --drop table "APP"."USUARIO_EXAMEN";
    create table "APP"."USUARIO_EXAMEN"(
        "ID" BIGINT not null generated always as identity,
       "EXA_ID" BIGINT not null,
       "USR_NOMBRE" VARCHAR(200) not null,
        constraint "SQL100326234106610" primary key ("ID")
    );

    create unique index "SQL100326234106610" on "APP"."USUARIO_EXAMEN"("ID");
    create index "SQL100326234107820" on "APP"."USUARIO_EXAMEN"("USR_NOMBRE");
    create index "SQL100326234107970" on "APP"."USUARIO_EXAMEN"("EXA_ID");
    

	alter table "APP"."EXAMEN_DETALLE" add constraint "FK_00006" foreign key ("EXA_ID") references "APP"."EXAMEN"("EXA_ID");
	
	alter table "APP"."PERSONA_EXAMEN"  add constraint "FK_00008" foreign key ("EXA_ID") references "APP"."EXAMEN"("EXA_ID");
    alter table "APP"."PERSONA_EXAMEN" add constraint "FK_00007" foreign key ("PER_ID") references "APP"."PERSONA"("PER_ID");
	
	alter table "APP"."PERSONA_RESTRICION"  add constraint "FKF22956E330CD3144"  foreign key ("PER_ID") references "APP"."PERSONA"("PER_ID");
	
	alter table "APP"."RESULTADO" add constraint "FK_000011" foreign key ("RDE_ID") references "APP"."RESULTADO_DETALLE_EXAMEN"("RDE_ID");
	
    alter table "APP"."RESULTADO_DETALLE_EXAMEN" add constraint "FK_000010" foreign key ("EXAD_ID") references "APP"."EXAMEN_DETALLE"("EXAD_ID");
    alter table "APP"."RESULTADO_DETALLE_EXAMEN" add constraint "FK_00009" foreign key ("PEXA_ID") references "APP"."PERSONA_EXAMEN"("PEXA_ID");

    alter table "APP"."USUARIO_EXAMEN" add constraint "FK60D83F59BDA1FC79" foreign key ("EXA_ID") references "APP"."EXAMEN"("EXA_ID");
    alter table "APP"."USUARIO_EXAMEN" add constraint "FK60D83F597277DE1D" foreign key ("USR_NOMBRE") references "APP"."USUARIO"("USR_NOMBRE");
        
    alter table "APP"."USUARIO_PERMISO" add constraint "FKE097C34430CA612D" foreign key ("PER_ID") references "APP"."PERMISO"("PER_ID");
    alter table "APP"."USUARIO_PERMISO" add constraint "FKE097C3447277DE1D" foreign key ("USR_NOMBRE") references "APP"."USUARIO"("USR_NOMBRE");
