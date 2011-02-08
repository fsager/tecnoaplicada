select per.*,perexa.*,exa.*,detalleRes.*,detalle.*,
		(select distinct 'SI' from app.persona p, app.persona_restricion pr,app.dominio d
		where p.per_id =pr.per_id
		  and d.dom_id=pr.dom_id
		  and d.dom_clave = 'Restricción Visual'
		  and p.per_id = per.per_id) utiliza_Correcion
 from app.persona per,
              app.persona_examen perexa,
              app.examen exa,
              app.resultado_detalle_examen detalleRes,
	      app.examen_detalle detalle
where perexa.per_id = per.per_id
    and exa.exa_id=perexa.exa_id
    and perexa.pexa_id= detalleRes.pexa_id
    and detalle.exad_id = detalleRes.exad_id
    and perexa.pexa_id = $P{p_pexa_id}
order by detalle.exad_detalle

select 1 from app.persona p, app.persona_restricion pr,app.dominio d
where p.per_id =pr.per_id
  and d.dom_id=pr.dom_id
  and d.dom_clave = 'Restricción Visual'
  --and p.per_id = 
  --and dom_id=19;

select per.*,perexa.*,exa.*,detalleRes.*,detalle.*
 from app.persona per,
              app.persona_examen perexa,
              app.examen exa,
              app.resultado_detalle_examen detalleRes,
	      app.examen_detalle detalle,
	      app.RESULTADO res
where perexa.per_id = per.per_id
    and exa.exa_id=perexa.exa_id
    and perexa.pexa_id= detalleRes.pexa_id
    and detalle.exad_id = detalleRes.exad_id
    and detalleRes.rde_id = res.rde_id    
    --and perexa.pexa_id = $P{p_pexa_id}
    and detalle.exad_codigo = 'TEST_AGUDEZA_VISUAL_CERCANA'
    --and res.res_etapa=1--derecho
order by detalle.exad_detalle



select * from app.persona per,
              app.persona_examen perexa,
              app.examen exa,
              app.resultado_detalle_examen detalleRes,
	      app.examen_detalle detalle	      
where perexa.per_id = per.per_id
    and exa.exa_id=perexa.exa_id
    and perexa.pexa_id= detalleRes.pexa_id
    and detalle.exad_id = detalleRes.exad_id
    --and perexa.pexa_id = 2
order by detalle.exad_detalle

1 1

/*

delete from APP.PROPIEDAD
where PROP_CLAVE = 'EXAMEN.PALANCA.ERRORES.PERMITIDOS.HASTA'

INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.PALANCA.ERRORES.PERMITIDOS.HASTA','13');


*/
/*delete from app.permiso;
delete from APP.DOMINIO;
delete from APP.PROPIEDAD;
delete from app.usuario;
alter table app.EXAMEN_DETALLE 
delete from app.EXAMEN;*/



update app.propiedad
  set prop_valor = '7'
where prop_clave = 'EXAMEN.REACION.MULTIPLE.COND.ERRORES.PERMITIDOS.HASTA';

update APP.EXAMEN_DETALLE 
  set EXAD_PARAMETROS_CORRECCION= '<ul>
										<li>Errores permitidos menor a ' ||(select prop_valor 
																			  from app.propiedad
																			where prop_clave = 'EXAMEN.PALANCA.ERRORES.PERMITIDOS.HASTA')||'.</li>
										<li>Tiempo fuera del circuito menor a  ' ||(select char(prop_valor/10) 
																					  from app.propiedad
																					where prop_clave = 'EXAMEN.PALANCA.ERRORES.TIEMPO')||' Centésimas de segundos.</li>
										<li>Punto sin activar menor a   '       ||(select prop_valor 
																			  from app.propiedad
																			where prop_clave = 'EXAMEN.PALANCA.ERRORES.PUNTOS.SIN.ACTIVAR.HASTA')||'.</li>
									</ul>'
where EXAD_CODIGO = 'TEST_COOR_BIMANUAL_FINA';



update APP.EXAMEN_DETALLE 
  set EXAD_PARAMETROS_CORRECCION= 'Tiempo promedio máximo '  ||(select char(prop_valor/10) 
																						  from app.propiedad
																						where prop_clave = 'EXAMEN.REACCION.SIMPLE.TIEMPO.PERMITIDOS.HASTA')||' Centésimas de segundos.'
where EXAD_CODIGO = 'TEST_REAC_SIMPLE';


update APP.EXAMEN_DETALLE 
  set EXAD_PARAMETROS_CORRECCION= '<ul>
																						<li>Errores permitidos menor a '  ||(select prop_valor 
																												  from app.propiedad
																												where prop_clave = 'EXAMEN.REACION.MULTIPLE.COND.ERRORES.PERMITIDOS.HASTA')||'.</li>
																						<li>Tiempo promedio menor a '  ||(select prop_valor 
																												  from app.propiedad
																												where prop_clave = 'EXAMEN.REACION.MULTIPLE.COND.TIEMPO.PERMITIDOS.HASTA')||'.</li>
																					</ul>'
where EXAD_CODIGO = 'TEST_REAC_MULTIPLES_COND';

update APP.EXAMEN
  set EXA_ORDEN = 2
where EXA_CODIGO='EXA_VISION';

update APP.EXAMEN
  set EXA_ORDEN = 3
where EXA_CODIGO='EXA_AUDICION';

update APP.EXAMEN
  set EXA_ORDEN = 1
where EXA_CODIGO='EXA_PSICOMETRICO';

update APP.EXAMEN
  set EXA_ORDEN = 4
where EXA_CODIGO='EXA_PERSONALIDAD';

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


select * from app.PERSONA_EXAMEN;


/*Insert para reportes*/

/*Iteracion 1*/


delete  from app.RESULTADO;
delete  from app.RESULTADO_DETALLE_EXAMEN;
delete  from app.PERSONA_EXAMEN;



select * from APP.PERSONA_EXAMEN;

insert into APP.PERSONA_EXAMEN (PER_ID,EXA_ID,PEXA_FECHA,PEXA_RESULTADO,PEXA_ESTADO,PEXA_NOTA,PEXA_OBS,PEXA_ADJ,PEXA_RESULTADO_MEDICO,PEXA_NOMBRE_ADJUNTO,PEXA_TIPO_EXAMEN) values 
(2,3,CURRENT TIMESTAMP,'DENTRO DE LOS PARAMETROS','FINALIZADO',2,null,null,'APROBADO',null,'Profecional');
insert into APP.PERSONA_EXAMEN (PER_ID,EXA_ID,PEXA_FECHA,PEXA_RESULTADO,PEXA_ESTADO,PEXA_NOTA,PEXA_OBS,PEXA_ADJ,PEXA_RESULTADO_MEDICO,PEXA_NOMBRE_ADJUNTO,PEXA_TIPO_EXAMEN) values 
(2,3,CURRENT TIMESTAMP,'DENTRO DE LOS PARAMETROS','FINALIZADO',null,null,null,'APROBADO',null,'Profecional');
insert into APP.PERSONA_EXAMEN (PER_ID,EXA_ID,PEXA_FECHA,PEXA_RESULTADO,PEXA_ESTADO,PEXA_NOTA,PEXA_OBS,PEXA_ADJ,PEXA_RESULTADO_MEDICO,PEXA_NOMBRE_ADJUNTO,PEXA_TIPO_EXAMEN) values 
(2,3,CURRENT TIMESTAMP,'DENTRO DE LOS PARAMETROS','FINALIZADO',null,null,null,'APROBADO',null,'Profecional');
insert into APP.PERSONA_EXAMEN (PER_ID,EXA_ID,PEXA_FECHA,PEXA_RESULTADO,PEXA_ESTADO,PEXA_NOTA,PEXA_OBS,PEXA_ADJ,PEXA_RESULTADO_MEDICO,PEXA_NOMBRE_ADJUNTO,PEXA_TIPO_EXAMEN) values 
(2,3,CURRENT TIMESTAMP,'DENTRO DE LOS PARAMETROS','FINALIZADO',null,null,null,'APROBADO',null,'Profecional');
insert into APP.PERSONA_EXAMEN (PER_ID,EXA_ID,PEXA_FECHA,PEXA_RESULTADO,PEXA_ESTADO,PEXA_NOTA,PEXA_OBS,PEXA_ADJ,PEXA_RESULTADO_MEDICO,PEXA_NOMBRE_ADJUNTO,PEXA_TIPO_EXAMEN) values 
(2,3,CURRENT TIMESTAMP,'DENTRO DE LOS PARAMETROS','FINALIZADO',null,null,null,'APROBADO',null,'Profecional');
insert into APP.PERSONA_EXAMEN (PER_ID,EXA_ID,PEXA_FECHA,PEXA_RESULTADO,PEXA_ESTADO,PEXA_NOTA,PEXA_OBS,PEXA_ADJ,PEXA_RESULTADO_MEDICO,PEXA_NOMBRE_ADJUNTO,PEXA_TIPO_EXAMEN) values 
(2,3,CURRENT TIMESTAMP,'DENTRO DE LOS PARAMETROS','FINALIZADO',null,null,null,'APROBADO',null,'Profecional');


select * from APP.PERSONA_EXAMEN;

insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (2,10,'FUERA DE LOS PARAMETROS',600,10);--EXAD_CODIGO_TEST_COOR_BIMANUAL  tiempo centecimas de segundos/errores 
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (2,11,'FUERA DE LOS PARAMETROS',239,8);--EXAD_CODIGO_TEST_COOR_BIMANUAL  tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (2,12,'FUERA DE LOS PARAMETROS',1000,12);--EXAD_CODIGO_TEST_COOR_BIMANUAL  tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (2,14,'FUERA DE LOS PARAMETROS',123,9);--EXAD_CODIGO_TEST_COOR_BIMANUAL  tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (2,13,'FUERA DE LOS PARAMETROS',634,6);--EXAD_CODIGO_TEST_COOR_BIMANUAL  tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (2,4,'FUERA DE LOS PARAMETROS',534,5);--EXAD_CODIGO_TEST_COOR_BIMANUAL  tiempo centecimas de segundos/errores


insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (4,83,'FUERA DE LOS PARAMETROS',null,3);--EXAD_CODIGO_TEST_COOR_VISOMOTORA errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (4,84,'FUERA DE LOS PARAMETROS',null,5);--EXAD_CODIGO_TEST_COOR_VISOMOTORA (Punteo) errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (4,85,'FUERA DE LOS PARAMETROS',null,7);--EXAD_CODIGO_TEST_COOR_VISOMOTORA (Punteo) errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (4,86,'FUERA DE LOS PARAMETROS',null,15);--EXAD_CODIGO_TEST_COOR_VISOMOTORA (Punteo) errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (4,87,'FUERA DE LOS PARAMETROS',null,44);--EXAD_CODIGO_TEST_COOR_VISOMOTORA (Punteo) errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (4,88,'FUERA DE LOS PARAMETROS',null,22);--EXAD_CODIGO_TEST_COOR_VISOMOTORA (Punteo) errores


insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (8,83,'FUERA DE LOS PARAMETROS',45,null);--EXAD_CODIGO_TEST_REAC_SIMPLE tiempo centecimas de segundos
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (8,84,'FUERA DE LOS PARAMETROS',56,null);--EXAD_CODIGO_TEST_REAC_SIMPLE tiempo centecimas de segundos
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (8,85,'FUERA DE LOS PARAMETROS',53,null);--EXAD_CODIGO_TEST_REAC_SIMPLE tiempo centecimas de segundos
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (8,86,'FUERA DE LOS PARAMETROS',23,null);--EXAD_CODIGO_TEST_REAC_SIMPLE tiempo centecimas de segundos
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (8,87,'FUERA DE LOS PARAMETROS',32,null);--EXAD_CODIGO_TEST_REAC_SIMPLE tiempo centecimas de segundos
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (8,88,'FUERA DE LOS PARAMETROS',56,null);--EXAD_CODIGO_TEST_REAC_SIMPLE tiempo centecimas de segundos

insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (1,83,'FUERA DE LOS PARAMETROS',4,null);--EXAD_CODIGO_TEST_CTR_TEMPORO metros
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (1,84,'FUERA DE LOS PARAMETROS',3.9,null);--EXAD_CODIGO_TEST_CTR_TEMPORO metros
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (1,85,'FUERA DE LOS PARAMETROS',3.9,null);--EXAD_CODIGO_TEST_CTR_TEMPORO metros
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (1,86,'FUERA DE LOS PARAMETROS',5.5,null);--EXAD_CODIGO_TEST_CTR_TEMPORO metros
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (1,87,'FUERA DE LOS PARAMETROS',4.6,null);--EXAD_CODIGO_TEST_CTR_TEMPORO metros
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (1,88,'FUERA DE LOS PARAMETROS',9.2,null);--EXAD_CODIGO_TEST_CTR_TEMPORO metros
	 

insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (3,83,'FUERA DE LOS PARAMETROS',400,10);--EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA (Palanca) tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (3,84,'FUERA DE LOS PARAMETROS',333,10);--EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA (Palanca) tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (3,85,'FUERA DE LOS PARAMETROS',343,12);--EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA (Palanca) tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (3,86,'FUERA DE LOS PARAMETROS',243,8);--EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA (Palanca) tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (3,87,'FUERA DE LOS PARAMETROS',221,10);--EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA (Palanca) tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (3,88,'FUERA DE LOS PARAMETROS',200,5);--EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA (Palanca) tiempo centecimas de segundos/errores

insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (7,83,'FUERA DE LOS PARAMETROS',90,4);--EXAD_CODIGO_TEST_REAC_MULTIPLES_COND tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (7,84,'FUERA DE LOS PARAMETROS',83,6);--EXAD_CODIGO_TEST_REAC_MULTIPLES_COND tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (7,85,'FUERA DE LOS PARAMETROS',86,7);--EXAD_CODIGO_TEST_REAC_MULTIPLES_COND tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (7,86,'FUERA DE LOS PARAMETROS',109,3);--EXAD_CODIGO_TEST_REAC_MULTIPLES_COND tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (7,87,'FUERA DE LOS PARAMETROS',222,7);--EXAD_CODIGO_TEST_REAC_MULTIPLES_COND tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (7,88,'FUERA DE LOS PARAMETROS',230,8);--EXAD_CODIGO_TEST_REAC_MULTIPLES_COND tiempo centecimas de segundos/errores


insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (6,83,'FUERA DE LOS PARAMETROS',110,10);--EXAD_CODIGO_TEST_REAC_MULT_NO_COND tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (6,84,'FUERA DE LOS PARAMETROS',97,12);--EXAD_CODIGO_TEST_REAC_MULT_NO_COND tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (6,85,'FUERA DE LOS PARAMETROS',57,11);--EXAD_CODIGO_TEST_REAC_MULT_NO_COND tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (6,86,'FUERA DE LOS PARAMETROS',44,15);--EXAD_CODIGO_TEST_REAC_MULT_NO_COND tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (6,87,'FUERA DE LOS PARAMETROS',60,32);--EXAD_CODIGO_TEST_REAC_MULT_NO_COND tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (6,88,'FUERA DE LOS PARAMETROS',20,12);--EXAD_CODIGO_TEST_REAC_MULT_NO_COND tiempo centecimas de segundos/errores


insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (5,83,'FUERA DE LOS PARAMETROS',70,2);--EXAD_CODIGO_TEST_PERC_REAC tiempo centecimas de segundos/errores	
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (5,84,'FUERA DE LOS PARAMETROS',22,3);--EXAD_CODIGO_TEST_PERC_REAC tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (5,85,'FUERA DE LOS PARAMETROS',36,6);--EXAD_CODIGO_TEST_PERC_REAC tiempo centecimas de segundos/errores	
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (5,86,'FUERA DE LOS PARAMETROS',38,8);--EXAD_CODIGO_TEST_PERC_REAC tiempo centecimas de segundos/errores
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (5,87,'FUERA DE LOS PARAMETROS',55,8);--EXAD_CODIGO_TEST_PERC_REAC tiempo centecimas de segundos/errores				
insert into app.resultado_detalle_examen (EXAD_ID, PEXA_ID, RDE_RESULTADO,RDE_NOTA,RDE_NOTA2) 
     values (5,88,'FUERA DE LOS PARAMETROS',46,19);--EXAD_CODIGO_TEST_PERC_REAC tiempo centecimas de segundos/errores


/*Insert para reportes*/

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


select * from app.examen_detalle;