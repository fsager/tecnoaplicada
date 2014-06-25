INSERT INTO app.persona (per_apellido,per_nombre,per_tipo_doc,per_numero_doc,per_sexo,
	per_fecha_nacimiento,per_estado_civil,per_grupo_sanguineo,
	per_telefono,per_celular,per_correo,per_estudios,per_domicilio,per_observaciones,per_foto,per_firma,
	per_localidad,per_educacion,per_licencia_de_conducir,per_tiempo_que_lleva_conduciendo,per_toma_medicamentos_regularmente_sn
	,per_tomo_hoy_psicofarmacos,per_tomo_hoy_alcohol)
	SELECT per_apellido,per_nombre,per_tipo_doc,per_numero_doc,per_sexo,
	per_fecha_nacimiento,per_estado_civil,per_grupo_sanguineo,
	per_telefono,per_celular,per_correo,per_estudios,per_domicilio,per_observaciones,per_foto,per_firma,
	per_localidad,per_educacion,per_licencia_de_conducir,per_tiempo_que_lleva_conduciendo,per_toma_medicamentos_regularmente_sn
	,per_tomo_hoy_psicofarmacos,per_tomo_hoy_alcohol
	FROM app.persona;

insert into APP.PERSONA_EXAMEN (PER_ID,EXA_ID,PEXA_FECHA,PEXA_RESULTADO,PEXA_ESTADO,PEXA_NOTA,PEXA_OBS,PEXA_ADJ,PEXA_RESULTADO_MEDICO,PEXA_NOMBRE_ADJUNTO,PEXA_TIPO_EXAMEN)
select PER_ID,EXA_ID,PEXA_FECHA,PEXA_RESULTADO,PEXA_ESTADO,PEXA_NOTA,PEXA_OBS,PEXA_ADJ,PEXA_RESULTADO_MEDICO,PEXA_NOMBRE_ADJUNTO,PEXA_TIPO_EXAMEN
from APP.PERSONA_EXAMEN ;

insert into app.RESULTADO_DETALLE_EXAMEN (EXAD_ID,PEXA_ID,RDE_RESULTADO,RDE_DETALLE_RESULTADO,RDE_PARAMETROS_CORRECCION,
RDE_NOTA,RDE_NOTA2,RDE_NOTA3,RDE_NOTA4,RDE_IMAGEN)
select EXAD_ID,PEXA_ID,RDE_RESULTADO,RDE_DETALLE_RESULTADO,RDE_PARAMETROS_CORRECCION,
RDE_NOTA,RDE_NOTA2,RDE_NOTA3,RDE_NOTA4,RDE_IMAGEN from APP.RESULTADO_DETALLE_EXAMEN;

insert into app.RESULTADO (RES_ETAPA,RDE_ID,RES_VALOR1,RES_VALOR2,RES_ETAPA_DESC)
select RES_ETAPA,RDE_ID,RES_VALOR1,RES_VALOR2,RES_ETAPA_DESC from app.RESULTADO;


alter table "APP"."EXAMEN" add EXA_LICENCED VARCHAR(1) not null default 'S';
alter table "APP"."EXAMEN_DETALLE" add EXAD_LICENCED VARCHAR(1) not null default 'S';


select detalleRes.*,detalle.*,perexa.*,exa.*,
		CASE
		 WHEN exad_codigo='TEST_CTR_TEMPORO' THEN 'Metros'
		 WHEN exad_codigo='TEST_PERC_REAC' THEN 'Tiempo en responder'
		 WHEN exad_codigo='TEST_REAC_MULTIPLES_COND' THEN 'Tiempo en responder'
		 WHEN exad_codigo='TEST_REAC_SIMPLE' THEN 'Tiempo en responder'
		 WHEN exad_codigo='TEST_COOR_BIMANUAL_FINA' THEN 'Tiempo fuera del circuito'
		 WHEN exad_codigo='TEST_REAC_MULT_NO_COND' THEN 'Tiempo en responder'
		 WHEN exad_codigo='TEST_COOR_VISOMOTORA' THEN ''
		 WHEN exad_codigo='TEST_COOR_BIMANUAL' THEN 'Tiempo fuera del circuito'
		 END interpretacion_nota1,
		CASE
		WHEN exad_codigo='TEST_CTR_TEMPORO' THEN ''
		 WHEN exad_codigo='TEST_PERC_REAC' THEN 'Errores'
		 WHEN exad_codigo='TEST_REAC_MULTIPLES_COND' THEN 'Errores'
		 WHEN exad_codigo='TEST_REAC_SIMPLE' THEN ''
		 WHEN exad_codigo='TEST_COOR_BIMANUAL_FINA' THEN 'Puntos sin activar'
		 WHEN exad_codigo='TEST_REAC_MULT_NO_COND' THEN 'Errores'
		 WHEN exad_codigo='TEST_COOR_VISOMOTORA' THEN 'Errores'
		 WHEN exad_codigo='TEST_COOR_BIMANUAL' THEN 'Errores'
		 END interpretacion_nota2
		 ,(select avg(rde_nota) from app.RESULTADO_DETALLE_EXAMEN res where res.exad_id = detalle.exad_id and res.pexa_id in $P!{p_pexa_id}) promedio_nota
		 ,(select avg(rde_nota2) from app.RESULTADO_DETALLE_EXAMEN res where res.exad_id = detalle.exad_id and res.pexa_id in $P!{p_pexa_id}) promedio_nota2
  from app.persona per,
              app.persona_examen perexa,
              app.examen exa,
              app.resultado_detalle_examen detalleRes,
	      app.examen_detalle detalle
where perexa.per_id = per.per_id
    and exa.exa_id=perexa.exa_id
    and perexa.pexa_id= detalleRes.pexa_id
    and detalle.exad_id = detalleRes.exad_id
    and perexa.pexa_id in $P!{p_pexa_id}
order by detalle.exad_orden,detalleRes.exad_id,detalle.exad_id


select avg(rde_nota) from app.RESULTADO_DETALLE_EXAMEN


select d.DOM_VALOR_MOSTRAR from app.persona p, app.persona_restricion pr,app.dominio d
where p.per_id =pr.per_id
  and d.dom_id=pr.dom_id
  and d.dom_clave = 'Restricción Visual'
  and (lower(d.DOM_VALOR_MOSTRAR) like lower('Usa Lentes de Contacto') or lower(d.DOM_VALOR_MOSTRAR) like lower('Usa Anteojos')) 
  and p.per_id = 1
  
  


select per.*,perexa.*,exa.*,detalleRes.*,detalle.*,
		(select distinct 'SI' from app.persona p, app.persona_restricion pr,app.dominio d
		where p.per_id =pr.per_id
		  and d.dom_id=pr.dom_id
		  and d.dom_clave = 'Restricción Visual'
		  and (lower(d.DOM_VALOR_MOSTRAR) like lower('Usa Lentes de Contacto') or lower(d.DOM_VALOR_MOSTRAR) like lower('Usa Anteojos'))
		  and p.per_id = per.per_id) utiliza_Correcion,
		  (select detalleResE.rde_detalle_resultado
		 from  app.persona_examen perexaE,
		 	   app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexaE.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and perexaE.pexa_id = perexa.pexa_id
		    and detalleE.exad_codigo = 'TEST_VISION_NOCTURNA') as vision_crepuscular,
		  (select detalleResE.rde_detalle_resultado
		 from  app.persona_examen perexaE,
		 	   app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexaE.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and perexaE.pexa_id = perexa.pexa_id
		    and detalleE.exad_codigo = 'TEST_ENCANDILAMIENTO') as deslumbramiento,
		  (select detalleResE.rde_detalle_resultado|| ' centécimas'
		 from  app.persona_examen perexaE,
		 	   app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexaE.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and perexaE.pexa_id = perexa.pexa_id
		    and detalleE.exad_codigo = 'TEST_REC_ENCANDILAMIENTO') as rec_encandilamiento,
		  (select detalleResE.rde_detalle_resultado
		 from  app.persona_examen perexaE,
		 	   app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexaE.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and perexaE.pexa_id = perexa.pexa_id
		    and detalleE.exad_codigo = 'TEST_FOTOCROMATICA') as vision_cromatica	,
		  (select detalleResE.rde_detalle_resultado
		 from  app.persona_examen perexaE,
		 	   app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexaE.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and perexaE.pexa_id = perexa.pexa_id
		    and detalleE.exad_codigo = 'TEST_CAMPIMETRIA') as campo_visual			    
 from app.persona per,
              app.persona_examen perexa,
              app.examen exa,
              app.resultado_detalle_examen detalleRes,
	      app.examen_detalle detalle
where perexa.per_id = per.per_id
    and exa.exa_id=perexa.exa_id
    and perexa.pexa_id= detalleRes.pexa_id
    and detalle.exad_id = detalleRes.exad_id
    and perexa.pexa_id = 15--$P{p_pexa_id}
order by detalle.exad_detalle;


select detalleRes.rde_detalle_resultado
		 from  app.persona_examen perexaE,
		 	   app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexaE.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and perexaE.pexa_id = 15
		    and detalleE.exad_codigo = 'TEST_ENCANDILAMIENTO'



select res.res_etapa_desc,
		CASE WHEN res.res_valor1=1 THEN '0.1' 
		 WHEN res.res_valor1=2 THEN '0.3'
		 WHEN res.res_valor1=3 THEN '0.4'
		 WHEN res.res_valor1=4 THEN '0.5'
		 WHEN res.res_valor1=5 THEN '0.7'
		 WHEN res.res_valor1=6 THEN '0.8'
		 WHEN res.res_valor1=7 THEN '1.0'
		 END res_descripcion,perexa.pexa_id,res.res_valor1
 from app.persona_examen perexa,
      app.resultado_detalle_examen detalleRes,
      app.EXAMEN_DETALLE detalle,
	  app.RESULTADO res
where perexa.pexa_id= detalleRes.pexa_id
  and detalleRes.rde_id = res.rde_id
  and detalle.exad_id = detalleRes.exad_id
  --and perexa.pexa_id = 24--$P{p_pexa_id}
  and detalle.exad_codigo = 'TEST_AGUDEZA_VISUAL_LEJANA'
  and res.res_etapa in (0,3)--binocular    
order by res.res_etapa


select res.res_etapa_desc
 from app.persona_examen perexa,
      app.resultado_detalle_examen detalleRes,
      app.EXAMEN_DETALLE detalle,
	  app.RESULTADO res
where perexa.pexa_id= detalleRes.pexa_id
  and detalleRes.rde_id = res.rde_id
  and detalle.exad_id = detalleRes.exad_id
  --and perexa.pexa_id =$P{p_pexa_id}
  and detalle.exad_codigo = 'TEST_CAMPIMETRIA'
  and res.res_etapa_desc like ('%izquierda%')    
order by res.res_etapa


select res.res_etapa_desc,perexa.pexa_id 
 from app.persona_examen perexa,
      app.resultado_detalle_examen detalleRes,
      app.EXAMEN_DETALLE detalle,
	  app.RESULTADO res
where perexa.pexa_id= detalleRes.pexa_id
  and detalleRes.rde_id = res.rde_id
  and detalle.exad_id = detalleRes.exad_id
  and perexa.pexa_id =26
  and detalle.exad_codigo = 'TEST_CAMPIMETRIA'
  and res.res_etapa_desc like ('%Nasal%')
order by res.res_etapa




select max(pexa_id) from app.persona_examen perexa


select * from app.RESULTADO res;

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