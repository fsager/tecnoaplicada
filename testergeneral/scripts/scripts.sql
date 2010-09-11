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
