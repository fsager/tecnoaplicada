select * from app.CARNET_LICENCIAS
 order by CLI_FECHA_IMPRESION desc;

select PER_NUMERO_DOC,CLI_FECHA_IMPORT,CLI_FECHA_IMPRESION from app.CARNET_LICENCIAS
  where PER_NUMERO_DOC like '%28958136%'                              
 order by CLI_FECHA_IMPORT desc;
 
10811825
28958136


select max(lic_Numero) from app.licencia where lic_Numero > 1000 and lic_Numero < 2000;

select * from app.licencia where lic_Numero >= 0 and lic_Numero <= 200000;



select * from app.licencia
order by lic_fecha_otorgada desc;

select 	per_apellido
		,per_numero_doc
		,per_fecha_nacimiento
		,lic_fecha_otorgada
		,lic_fecha_vencimiento
 from app.carnet_licencias
 order by per_numero_doc;
 


select * from app.persona
where per_numero_doc = '36488625';


delete  from  app.carnet_licencias;

delete  from app.dominio
where dom_clave = 'LICENCIAS_IMPORTADAS_CANTIDAD';


update app.antecedente
  set ant_descripcion = 'No disponibles'
where ant_Id = 1;


select *
from APP.CLASE_LICENCIA this_ where (lcase(this_.CLL_NOMBRE_CLASE) like 'a-1') order by this_.CLL_NOMBRE_CLASE asc;

select *
from APP.CLASE_LICENCIA;

select * from APP.CARNET_LICENCIAS;

select * from app.persona;


delete  from app.clase_licencia;
delete  from app.persona;
delete  from app.Antecedente;
delete from app.persona;
delete from app.usuario;
delete from app.licencia;
delete from app.caja;
delete from app.carnet_licencias;

select * from app.Antecedente;

select * from exportados 
  where licencia not in (select codigoLicencia from Licencias);
  

select * from app.persona_examen ;

delete from app.usuario_permiso;

delete from app.permiso;

delete from app.usuario_examen;

delete from app.examen;

delete from app.usuario;

delete from app.USUARIO_PERMISO;

delete from app.USUARIO_EXAMEN;

delete from app.auditoria;

DELETE FROM APP.EXAMEN;


delete from app.usuario
where usr_id = -1;

delete 
 from APP.DOMINIO 
where DOM_CODIGO = 'Tipo Documento';

DELETE FROM APP.DOMINIO;

SELECT DISTINCT lic_tramite from APP.LICENCIA;

SELECT * FROM APP.PROPIEDAD;


select *
 from APP.LICENCIA
 order by lic_id desc;
 
 select max(lic_numero)
 from APP.LICENCIA;
 
 
 select * from APP.CARNET_LICENCIAS
   order by 1 asc;
   
   
 select * from APP.CARNET_LICENCIAS
   where cli_id like '10004';
                      100000
                      
delete from APP.CARNET_LICENCIAS;


DELETE FROM APP.DOMINIO
where DOM_TIPO = 'LICENCIAS_IMPORTADAS_CANTIDAD';


,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION
 