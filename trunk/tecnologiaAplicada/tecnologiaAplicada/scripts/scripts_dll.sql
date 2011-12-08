--alter table `jttecnol_lic`.`ACTIVACION` drop `ACT_CODIGO_SEGURIDAD`;
--alter table `jttecnol_lic`.`LICENCIA` add `LIC_DATOS_ADICIONALES` VARCHAR(700);
--alter table `jttecnol_lic`.`LICENCIA` drop `LIC_PRODUCTO`;
--alter table `jttecnol_lic`.`LICENCIA` add `LIC_PRODUCTO` VARCHAR(300) not null default 'TesterGeneral';





drop table `jttecnol_lic`.`USUARIO`;
drop table `jttecnol_lic`.`PROPIEDAD`;
drop table `jttecnol_lic`.`DOMINIO`;
drop table `jttecnol_lic`.`ACTIVACION`;
drop table `jttecnol_lic`.`DETALLE_LICENCIA`;
drop table `jttecnol_lic`.`EXAMEN_DETALLE`;
drop table `jttecnol_lic`.`EXAMEN`;
drop table `jttecnol_lic`.`LICENCIA`;
drop table `jttecnol_lic`.`CLIENTE`;

CREATE  TABLE jttecnol_lic.USUARIO (
  `USR_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `USR_NOMBRE` VARCHAR(200) NOT NULL ,
  `USR_CLAVE` VARCHAR(20) NOT NULL ,
  `USR_ULTIMO_ACCESO` DATETIME,
  `USR_HABILITADO_SN` VARCHAR(1) NOT NULL ,
  `DELETED_SN` VARCHAR(1) NOT NULL ,
  PRIMARY KEY (`USR_ID`) ,
  UNIQUE INDEX `USR_ID_UNIQUE` (`USR_ID` ASC),
  UNIQUE INDEX `USR_NOMBRE_UNIQUE` (`USR_NOMBRE` ASC) );

	
    create table `jttecnol_lic`.`DOMINIO`(
        `DOM_ID` BIGINT not null AUTO_INCREMENT,
       `DOM_TIPO` VARCHAR(200),
       `DOM_CLAVE` VARCHAR(200),
       `DOM_CODIGO` VARCHAR(200),
       `DOM_VALOR_MOSTRAR` VARCHAR(200),
       `DOM_DESCRIPCION` VARCHAR(200),
       PRIMARY KEY (`DOM_ID`),
	   UNIQUE INDEX `DOM_ID_UNIQUE` (`DOM_ID` ASC)
    );

	create unique index `UNIQUE_DOMINIO` on `jttecnol_lic`.`DOMINIO`(`DOM_TIPO`,`DOM_CLAVE`,`DOM_CODIGO`,`DOM_VALOR_MOSTRAR`,`DOM_DESCRIPCION`);
    
    create table `jttecnol_lic`.`EXAMEN`(
       `EXA_ID` BIGINT not null AUTO_INCREMENT,
       `EXA_CODIGO` VARCHAR(20) not null unique,
       `EXA_NOMBRE` VARCHAR(100) not null,
       `EXA_ORDEN` BIGINT not null,
       PRIMARY KEY (`EXA_ID`),
	   UNIQUE INDEX `EXA_ID_UNIQUE` (`EXA_ID` ASC),
	   UNIQUE INDEX `EXA_CODIGO_UNIQUE` (`EXA_CODIGO` ASC)
    );    
    
	
    create table `jttecnol_lic`.`EXAMEN_DETALLE`(
       `EXAD_ID` BIGINT not null AUTO_INCREMENT,
       `EXAD_DETALLE` VARCHAR(200) not null,
       `EXAD_CODIGO` VARCHAR(200) not null unique,
       `EXA_ID` BIGINT not null,
       `EXAD_ORDEN` BIGINT not null,
       `EXAD_PARAMETROS_CORRECCION` VARCHAR(4000),
       PRIMARY KEY (`EXAD_ID`),
       UNIQUE INDEX `EXAD_ID_UNIQUE` (`EXAD_ID` ASC),
	   UNIQUE INDEX `EXAD_CODIGO_UNIQUE` (`EXAD_CODIGO` ASC)
    );
	
	
    create table `jttecnol_lic`.`PROPIEDAD`(
       `PROP_CLAVE` VARCHAR(200) not null,
       `PROP_VALOR` VARCHAR(200) not null,
       `PROP_BLOB` BLOB,
       PRIMARY KEY (`PROP_CLAVE`),
       UNIQUE INDEX `PROP_CLAVE_UNIQUE` (`PROP_CLAVE` ASC)
    );
    
     create table `jttecnol_lic`.`CLIENTE`(
       `CLI_ID` BIGINT not null AUTO_INCREMENT,
	   `CLI_RAZON_SOCIAL` VARCHAR(200) not null,
	   `CLI_CUIT_CUIL` VARCHAR(200),
       `CLI_TELEFONO` VARCHAR(40),
       `CLI_CELULAR` VARCHAR(40),
       `CLI_CORREO` VARCHAR(60),
       `CLI_DOMICILIO` VARCHAR(200),
       `CLI_OBSERVACIONES` VARCHAR(200),
        PRIMARY KEY (`CLI_ID`) ,
		UNIQUE INDEX `CLI_ID_UNIQUE` (`CLI_ID` ASC) 
    );
	
     create table `jttecnol_lic`.`LICENCIA`(
       `LIC_ID` BIGINT not null AUTO_INCREMENT,
	   `LIC_NRO` VARCHAR(200) not null,
	   `LIC_TIPO` BIGINT not null,
	   `LIC_PRODUCTO` VARCHAR(300) not null,
	   `LIC_ACTIVA_SN` VARCHAR(1) not null,
	   `LIC_FECHA_GENERACION` DATETIME not null,
	   `CLI_ID` BIGINT not null,
	   `LIC_DATOS_ADICIONALES` VARCHAR(700),	   
        PRIMARY KEY (`LIC_ID`) ,
		UNIQUE INDEX `LIC_ID_UNIQUE` (`LIC_ID` ASC),
		UNIQUE INDEX `LIC_NRO_UNIQUE` (`LIC_NRO` ASC)
    );
	
	
     create table `jttecnol_lic`.`DETALLE_LICENCIA`(
        `DLIC_ID` BIGINT not null AUTO_INCREMENT,
	    `EXAD_ID` BIGINT not null,
	    `DLIC_ACTIVA_SN` VARCHAR(1) not null,
	    `LIC_ID` BIGINT not null,
        PRIMARY KEY (`DLIC_ID`) ,
		UNIQUE INDEX `DLIC_ID_UNIQUE` (`DLIC_ID` ASC)
    );
    
	
     create table `jttecnol_lic`.`ACTIVACION`(
        `ACT_ID` BIGINT not null AUTO_INCREMENT,
	    `ACT_FECHA` DATETIME not null,
	    `ACT_CODIGO` VARCHAR(200) not null,
	    `ACT_VERSION_APP` VARCHAR(200),
	    `LIC_ID` BIGINT not null,
        PRIMARY KEY (`ACT_ID`) ,
		UNIQUE INDEX `ACT_ID_UNIQUE` (`ACT_ID` ASC)
    );



alter table `jttecnol_lic`.`LICENCIA` add constraint `FK_00001` foreign key (`CLI_ID`) references `jttecnol_lic`.`CLIENTE`(`CLI_ID`);
alter table `jttecnol_lic`.`DETALLE_LICENCIA` add constraint `FK_00002` foreign key (`LIC_ID`) references `jttecnol_lic`.`LICENCIA`(`LIC_ID`);
alter table `jttecnol_lic`.`DETALLE_LICENCIA` add constraint `FK_00003` foreign key (`EXAD_ID`) references `jttecnol_lic`.`EXAMEN_DETALLE`(`EXAD_ID`);
alter table `jttecnol_lic`.`ACTIVACION` add constraint `FK_00004` foreign key (`LIC_ID`) references `jttecnol_lic`.`LICENCIA`(`LIC_ID`);
alter table `jttecnol_lic`.`EXAMEN_DETALLE` add constraint `FK_00006` foreign key (`EXA_ID`) references `jttecnol_lic`.`EXAMEN`(`EXA_ID`);