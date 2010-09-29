--delete from app.usuario_permiso
--delete from app.permiso
--delete from app.usuario_examen
--delete from app.examen

--Usuario administrador
--delete from app.usuario
--delete from app.USUARIO_PERMISO
--delete from app.USUARIO_EXAMEN
--delete from app.auditoria
--DELETE FROM APP.EXAMEN

--delete from APP.DOMINIO;



--delete from  APP.DOMINIO where DOM_TIPO = 'IMAGENES';

delete from app.PROPIEDAD;


delete from APP.PROPIEDAD where PROP_CLAVE = 'NOMBRE.IMAGEN.FONDO.SEGUNDO.MONITOR';



select * from app.PERSONA_EXAMEN;

select * from app.RESULTADO_DETALLE_EXAMEN;
select * from app.RESULTADO;

--delete from app.persona
--delete from app.PERSONA_RESTRICION


update APP.PROPIEDAD 
set PROP_VALOR='81'
where PROP_CLAVE ='EXAMEN.PALANCA.PUNTOS';

select * from APP.PROPIEDAD 
where PROP_CLAVE ='SISTEMA.FTP_PRINCIPAL_URL';


delete  from app.PERSONA_EXAMEN;

delete  from app.RESULTADO_DETALLE_EXAMEN;


delete  from app.RESULTADO;

select * from app.RESULTADO;


SELECT * FROM SYS.SYSTABLES T,SYS.SYSSCHEMAS S
where t.schemaid = s.schemaid
  and s.schemaname= 'APP';
SELECT * FROM app.PERSONA_EXAMEN;

select * from app.carnet_licencias;





select * from app.dominio
where dom_tipo='LICENCIAS_IMPORTADAS_CANTIDAD';

delete from app.dominio
where dom_tipo='LICENCIAS_IMPORTADAS_CANTIDAD';

delete from app.carnet_licencias;

--ENTRAR POR PERSONA_EXAMEn con estado finalizado
-- 1 EXAD_CODIGO_TEST_COOR_BIMANUAL unidades={"Centésima de segundos","Errores"};
-- 2 EXAD_CODIGO_TEST_COOR_VISOMOTORA unidades={"Centésima de segundos","Errores"};
-- 3 EXAD_CODIGO_TEST_REAC_SIMPLE unidades={"Centésimas de segundos","Errores"}
-- 4 EXAD_CODIGO_TEST_CTR_TEMPORO unidades={"Metros"};
-- 5 EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA (Palanca) unidades={"Centésimas de segundos","Errores"};
-- 6 EXAD_CODIGO_TEST_REAC_MULTIPLES_COND unidades={"Centésimas de segundos","Errores"};
-- 7 EXAD_CODIGO_TEST_REAC_MULT_NO_COND unidades={"Centésima de segundos","Errores"};
-- 8 EXAD_CODIGO_TEST_PERC_REAC unidades={"Centésimas de segundos","Errores"};

select * from app.resultado_detalle_examen;

--delete from app.resultado_detalle_examen;

insert into APP.PERSONA_EXAMEN (PER_ID,EXA_ID,PEXA_FECHA,PEXA_RESULTADO,PEXA_ESTADO,PEXA_NOTA,PEXA_OBS,PEXA_ADJ,PEXA_RESULTADO_MEDICO,PEXA_NOMBRE_ADJUNTO,PEXA_TIPO_EXAMEN) values 
(1,3,CURRENT TIMESTAMP,'DENTRO DE LOS PARAMETROS','FINALIZADO',2,null,null,'APROBADO',null,'Profecional');

select * from app.PERSONA_EXAMEN

insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (1,1,'FUERA DE LOS PARAMETROS',600,10);--EXAD_CODIGO_TEST_COOR_BIMANUAL  tiempo/metros errores 
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (2,1,'FUERA DE LOS PARAMETROS',4000,3);--EXAD_CODIGO_TEST_COOR_VISOMOTORA tiempo/metros errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (3,1,'FUERA DE LOS PARAMETROS',45,2);--EXAD_CODIGO_TEST_REAC_SIMPLE tiempo/metros errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (4,1,'FUERA DE LOS PARAMETROS',4,null);--EXAD_CODIGO_TEST_CTR_TEMPORO metros
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (5,1,'FUERA DE LOS PARAMETROS',4000,10);--EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA tiempo/metros errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (6,1,'FUERA DE LOS PARAMETROS',90,4);--EXAD_CODIGO_TEST_REAC_MULTIPLES_COND tiempo/metros errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (7,1,'FUERA DE LOS PARAMETROS',110,10);--EXAD_CODIGO_TEST_REAC_MULT_NO_COND tiempo/metros errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (8,1,'FUERA DE LOS PARAMETROS',70,2);--EXAD_CODIGO_TEST_PERC_REAC tiempo/metros errores	


select EXAD_DETALLE serie,
  from app.resultado_detalle_examen RDE,
  	   app.examen_detalle EXAD
 where EXAD.EXAD_ID=RDE.EXAD_ID;
     
select * from app.resultado_detalle_examen;



select * from app.resultado;

select * from 
APP.RESULTADO_DETALLE_EXAMEN this_ inner join APP.PERSONA_EXAMEN personaexa1_ 
on this_.PEXA_ID=personaexa1_.PEXA_ID inner join APP.EXAMEN_DETALLE examendeta2_ 
on this_.EXAD_ID=examendeta2_.EXAD_ID 
where (1=1) and personaexa1_.PEXA_ID = 8 
--and examendeta2_.EXAD_ID = 17 
order by this_.RDE_ID asc

348935 [AWT-EventQueue-0] DEBUG 133 org.hibernate.type.LongType  - binding '8' to parameter: 1
348944 [AWT-EventQueue-0] DEBUG 133 org.hibernate.type.LongType  - binding '17' to parameter: 2


select * from app.examen_detalle;