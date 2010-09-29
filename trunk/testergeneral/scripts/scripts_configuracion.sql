/*delete from app.permiso;
delete from APP.DOMINIO;
delete from APP.PROPIEDAD;
delete from app.usuario;

delete from app.EXAMEN;*/


INSERT INTO APP.PERMISO (PER_CODIGO,PER_DESCRIPCION) VALUES('AM_PERSONA','Registrar/Modificar Persona');
INSERT INTO APP.PERMISO (PER_CODIGO,PER_DESCRIPCION) VALUES('AM_USR','Registrar/Modificar Usuario');
INSERT INTO APP.PERMISO (PER_CODIGO,PER_DESCRIPCION) VALUES('B_PERSONA','Eliminar Persona');
INSERT INTO APP.PERMISO (PER_CODIGO,PER_DESCRIPCION) VALUES('B_USR','Eliminar Usuario');
INSERT INTO APP.PERMISO (PER_CODIGO,PER_DESCRIPCION) VALUES('ACCESO_PANEL_CONTROL','Acceso Panel de Control General');
INSERT INTO APP.PERMISO (PER_CODIGO,PER_DESCRIPCION) VALUES('ACCESO_LOG','Acceso Log de Eventos');
INSERT INTO APP.PERMISO (PER_CODIGO,PER_DESCRIPCION) VALUES('ACCESO_BACKUPS','Acceso a Backups');

--Examenes

/*delete from APP.USUARIO_EXAMEN;
delete from APP.EXAMEN_DETALLE;
delete from app.RESULTADO;
delete from app.RESULTADO_DETALLE_EXAMEN;
delete from app.PERSONA_EXAMEN;
delete from app.EXAMEN_DETALLE;
*/
--select * from APP.EXAMEN;

select * from app.EXAMEN_DETALLE;

INSERT INTO APP.EXAMEN (EXA_CODIGO,EXA_NOMBRE) VALUES('EXA_VISION','Examen de Visi�n');
INSERT INTO APP.EXAMEN (EXA_CODIGO,EXA_NOMBRE) VALUES('EXA_AUDICION','Examen de Audici�n');
INSERT INTO APP.EXAMEN (EXA_CODIGO,EXA_NOMBRE) VALUES('EXA_PSICOMETRICO','Examen Psicom�trico');
INSERT INTO APP.EXAMEN (EXA_CODIGO,EXA_NOMBRE) VALUES('EXA_PERSONALIDAD','Examen de Personalidad');

INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de coordinaci�n bimanual','TEST_COOR_BIMANUAL',3,1);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de coordinaci�n visomotora (Punteo)','TEST_COOR_VISOMOTORA',3,2);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de reacci�n simple','TEST_REAC_SIMPLE',3,3);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de control temporo espacial','TEST_CTR_TEMPORO',3,4);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de coordinaci�n bimanual fina (Palanca)','TEST_COOR_BIMANUAL_FINA',3,5);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de reaciones m�ltiples condicionada','TEST_REAC_MULTIPLES_COND',3,6);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de reacciones m�ltiples no condicionadas','TEST_REAC_MULT_NO_COND',3,7);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de percepci�n y reacci�n','TEST_PERC_REAC',3,8);



INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de agudeza visual','TEST_AGUDEZA_VISUAL',1,1);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de visi�n fotocrom�tica','TEST_FOTOCROMATICA',1,2);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de profundidad','TEST_PROFUNDIDAD',1,3);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de foria visual','TEST_FORIA',1,4);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de visi�n nocturna','TEST_VISION_NOCTURNA',1,5);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de encandilamiento','TEST_ENCANDILAMIENTO',1,6);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de recuperaci�n al encandilamiento','TEST_REC_ENCANDILAMIENTO',1,7);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de campimetr�a','TEST_CAMPIMETRIA',1,8);
INSERT INTO APP.EXAMEN_DETALLE (EXAD_DETALLE,EXAD_CODIGO,EXA_ID,EXAD_ORDEN) VALUES('Test de audio','TEST_AUDIO',1,9);


insert into app.usuario (USR_NOMBRE,USR_CLAVE,USR_ULTIMO_ACCESO,USR_HABILITADO_SN,DELETED_SN) values('Administrador Sistema','1',null,'S','N');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('GRAL','Tipo Documento','DNI','DNI','DNI');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Tipo Documento','LC','LC','LC');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Tipo Documento','PASAPORTE','PASAPORTE','PASAPORTE');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Sexo','MASCULINO','MASCULINO','M');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Sexo','FEMENINO','FEMENINO','F');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Estado Civil','CASADO','CASADO','CASADO');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Estado Civil','SOLTERO','SOLTERO','SOLTERO');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Estado Civil','SEPARADO','SEPARADO','SEPARADO');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Estado Civil','VIUDO','VIUDO','VIUDO');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','O-','O-','O-');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','O+','O+','O+');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','A-','A-','A-');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','A+','A+','A+');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','B-','B-','B-');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','B+','B+','B+');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','AB-','AB-','AB-');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','AB+','AB+','AB+');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Localidad','BERROTARAN','BERROTARAN','BERROTARAN');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('RESTRICCION','Restricci�n Visual','Usa Lentes de Contacto','Usa Lentes de Contacto','Usa Lentes de Contacto');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('RESTRICCION','Restricci�n Visual','Operaci�n Ocular','Operaci�n Ocular','Operaci�n Ocular');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('RESTRICCION','Restricci�n Visual','Usa Anteojos','Usa Anteojos','Usa Anteojos');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('RESTRICCION','Restricci�n Visual','Vision Monocular','Vision Monocular','Vision Monocular');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('RESTRICCION','Restricci�n Visual','Estrabismo','Estrabismo','Estrabismo');


INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('RESTRICCION','Restricci�n Auditiva','Operaci�n Auditiva','Operaci�n Auditiva','Operaci�n Auditiva');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('RESTRICCION','Restricci�n Auditiva','Usa Aud�fonos','Usa Aud�fonos','Usa Aud�fonos');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('RESTRICCION','Restricci�n F�sica','Pr�tesis Superior','Pr�tesis Superior','Pr�tesis Superior');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('RESTRICCION','Restricci�n F�sica','Pr�tesis Inferior','Pr�tesis Inferior','Pr�tesis Inferior');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('RESTRICCION','Restricci�n F�sica','Afecci�n Miembros Superiores','Afecci�n Miembros Superiores','Afecci�n Miembros Superiores');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('RESTRICCION','Restricci�n F�sica','Afecci�n Miembros Inferiores','Afecci�n Miembros Inferiores','Afecci�n Miembros Inferiores');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('IMAGENES','EXTENCION_IMAGENES','png','png','png');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('IMAGENES','EXTENCION_IMAGENES','jpg','jpg','jpg');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('IMAGENES','EXTENCION_IMAGENES','jpeg','jpeg','jpeg');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('IMAGENES','EXTENCION_IMAGENES','gif','gif','gif');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('IMAGENES','EXTENCION_IMAGENES','tif','tif','tif');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('IMAGENES','EXTENCION_IMAGENES','tiff','tiff','tiff');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('EXAMEN','EXAMEN_ESTADO','Aprobado','Aprobado','Aprobado');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('EXAMEN','EXAMEN_ESTADO','Reprobado','Reprobado','Reprobado');


INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('EXAMEN','TIPO_EXAMEN','Profesional','Profesional','Profesional');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('EXAMEN','TIPO_EXAMEN','Particular','Particular','Particular');

INSERT INTO APP.PROPIEDAD VALUES ('FORMATO.FECHA','dd-MM-yyyy',null);
INSERT INTO APP.PROPIEDAD VALUES ('FORMATO.HORA','HH:mm:ss',null);
INSERT INTO APP.PROPIEDAD VALUES ('FORMATO.FECHA.HORA','dd-MM-yyyy HH:mm:ss',null);
INSERT INTO APP.PROPIEDAD VALUES ('DIRECTORIO.IMAGENES','c:\\',null);
INSERT INTO APP.PROPIEDAD VALUES ('DIRECTORIO.BACKUP.PRIMARIO','D:\\',null);
INSERT INTO APP.PROPIEDAD VALUES ('DIRECTORIO.BACKUP.SECUNDARIO','C:\\',null);
INSERT INTO APP.PROPIEDAD VALUES ('DIRECTORIO.IMAGEN.FONDO.APLICACION','C:\\',null);
INSERT INTO APP.PROPIEDAD VALUES ('DIRECTORIO.IMAGEN.FONDO.SEGUNDO.MONITOR','C:\\',null);
INSERT INTO APP.PROPIEDAD VALUES ('PERSONA.FOTO.REQUERIDA','S',null);
INSERT INTO APP.PROPIEDAD VALUES ('PERSONA.FIRMA.REQUERIDA','N',null);
INSERT INTO APP.PROPIEDAD VALUES ('PERSONA.DNI.MASCARA','##.###.###',null);
INSERT INTO APP.PROPIEDAD VALUES ('PERSONA.DNI.PERMITIR.SOLO.NUMEROS','S',null);
INSERT INTO APP.PROPIEDAD VALUES ('PERSONA.DNI.FIJAR.CANTIDAD.CARACTERES','S',null);
INSERT INTO APP.PROPIEDAD VALUES ('PERSONA.DNI.CANTIDAD.CARACTERES.FIJA','8',null);
INSERT INTO APP.PROPIEDAD VALUES ('SISTEMA.ACTUALIZACION.AUTOMATICA.INICIO','S',null);
INSERT INTO APP.PROPIEDAD VALUES ('SISTEMA.DEVICES.CAMARA','vfw:Microsoft WDM Image Capture (Win32):0',null);
INSERT INTO APP.PROPIEDAD VALUES ('SISTEMA.GUARDAR.LOG.EVENTOS','S',null);
INSERT INTO APP.PROPIEDAD VALUES ('SISTEMA.CONSERVAR.DATOS.LOG.EVENTOS.XDIAS','20',null);
INSERT INTO APP.PROPIEDAD VALUES ('SISTEMA.ORIGEN.FOTOS','DISCO',null);--DISCO WEBCAM EXISTE ENUM
INSERT INTO APP.PROPIEDAD VALUES ('SISTEMA.ORIGEN.FIRMAS','DISCO',null);--DISCO PAD EXISTE ENUM
INSERT INTO APP.PROPIEDAD VALUES ('SISTEMA.IMAGENES.PORCENTAJE.REDUCCION','25',null);
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.BACKUP.AUTOMATICO.ACTIVADO','S');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR)  VALUES ('SISTEMA.BACKUP.AUTOMATICO.CADAXDIAS','20');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.BACKUP.SECUNDARIO.SI-NO','N');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.BACKUP.FECHAULTIMOBACKUP','28-04-2010');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.IMAGEN.PRIMARIA','0');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.IMAGEN.SECUNDARIA','0');

INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FTP_PRINCIPAL_URL','DXTEn5m2NtfIb0JrQ88tOwkSptl3lH87AJZPfePBh80=');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FTP_PRINCIPAL_USER','K8l004OOY1J6Uup98Cp/pQ==');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FTP_PRINCIPAL_PASSWORD','uLBg40t0YiQ9/9dBV8UgSg==');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FTP_SECUNDARIO_URL','1yT7xdyXFOC1p1EecQ0V3Q==');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FTP_SECUNDARIO_USER','sva1t1RMRadkMMoiRlpKWA==');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FTP_SECUNDARIO_PASSWORD','WXr7F2JirExUiUzOnSAeyA==');


INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.HASH_ULTIMO_ARCHIVO_SCRIPT_EJECUTADO','0');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.NOMBRE.PROGRAMA','TesterGeneral');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.CODIGO_REGION_DESTINO','ar');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.VERSION_PRINCIPAL','v1');

--delete from APP.PROPIEDAD where prop_clave = 'EXAMEN.TAMA�O.PANTALLA.SECUNDARIA.WIDTH';
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.TAMA�O.PANTALLA.SECUNDARIA.HEIGHT','650',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.TAMA�O.PANTALLA.SECUNDARIA.WIDTH','1000',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.RESULTADOS.EXAMADO','S',null);--EXAMEN.RESULTADOS.EXAMINADO

--delete from APP.PROPIEDAD where prop_clave like 'EXAMEN.ANTICIPACION%';
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.ANTICIPACION.METROS.PERMITIDOS.HASTA','7',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.ANTICIPACION.APRENDIZAJE.METROS.TOTAL','100',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.ANTICIPACION.APRENDIZAJE.SPEED','60',null);--KM/H
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.ANTICIPACION.ETAPA1.SPEED','45',null);--KM/H
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.ANTICIPACION.ETAPA2.SPEED','75',null);--KM/H
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.ANTICIPACION.ETAPA3.SPEED','90',null);--KM/H
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.ANTICIPACION.ETAPA4.SPEED','85',null);--KM/H
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.ANTICIPACION.ETAPA5.SPEED','55',null);--KM/H
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.ANTICIPACION.ETAPA6.SPEED','45',null);--KM/H

--delete from app.propiedad where prop_clave like 'EXAMEN.PERCEPCION.REACCION%';
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.PERCEPCION.REACCION.ERRORES.PERMITIDOS.HASTA','10',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.PERCEPCION.REACCION.ETAPAS.EXAMEN','30',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.PERCEPCION.REACCION.ETAPAS.APRENDIZAJE','12',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.PERCEPCION.REACCION.TIEMPO.SLEEP','1000',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.PERCEPCION.REACCION.TIEMPO.ENTRE.TIEMPO','650',null);

INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.PERCEPCION.REACCION.TIEMPO.SLEEP.COND','2000',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.PERCEPCION.REACCION.TIEMPO.ENTRE.TIEMPO.COND','1200',null);

INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.PERCEPCION.REACCION.TIEMPO.SLEEP.NOCOND','2000',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.PERCEPCION.REACCION.TIEMPO.ENTRE.TIEMPO.NOCOND','1200',null);


--delete from app.propiedad where prop_clave like 'EXAMEN.REACION.MULTIPLE.COND%';
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.REACION.MULTIPLE.COND.ERRORES.PERMITIDOS.HASTA','10');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.REACION.MULTIPLE.COND.TIEMPO.PERMITIDOS.HASTA','6000');

--delete from app.propiedad where prop_clave like 'EXAMEN.REACION.MULTIPLE.NOCOND%';
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.REACION.MULTIPLE.NOCOND.ERRORES.PERMITIDOS.HASTA','10');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.REACION.MULTIPLE.NOCOND.TIEMPO.PERMITIDOS.HASTA','7000');

--delete from APP.PROPIEDAD where prop_clave like 'EXAMEN.COORDINACION.VISOMOTORA%';
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.LUZ','850');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.APRENDEZAJE.DURACION','20000');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.EXAMEN.DURACION','60000');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.COORDINACION.VISOMOTORA.ERRORES.PERMITIDOS.HASTA','10');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.DENTRO.MINIMO.PERMITIDO','20000');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.COORDINACION.VISOMOTORA.ENTRE.TIEMPO','450');

--delete from APP.PROPIEDAD where prop_clave like 'EXAMEN.COORDINACION.BIMANUAL%';
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.COORDINACION.BIMANUAL.VELOCIDAD.EXAMEN','5');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.COORDINACION.BIMANUAL.VELOCIDAD.APRENDIZAJE','4');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.COORDINACION.BIMANUAL.RUTAS.EXAMEN','images/coordinacionBimanual/rutas.jpg');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.COORDINACION.BIMANUAL.RUTAS.APRENDIZAJE','images/coordinacionBimanual/rutasAprend.jpg');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.COORDINACION.BIMANUAL.TIEMPO.FUERA.PERMITIDO','500');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.COORDINACION.BIMANUAL.ERRORES.PERMITIDO','30');


INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.REACCION.SIMPLE.ETAPAS.APRENDIZAJE','3',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.REACCION.SIMPLE.ETAPAS.EXAMEN','10',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.REACCION.SIMPLE.TIEMPO.VERDE.DESDE','2000',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.REACCION.SIMPLE.TIEMPO.VERDE.HASTA','5000',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.REACCION.SIMPLE.TIEMPO.PERMITIDOS.HASTA','500',null);
INSERT INTO APP.PROPIEDAD VALUES ('EXAMEN.REACCION.SIMPLE.ERRORES.PERMITIDOS.HASTA','3',null);


--delete from APP.PROPIEDAD where prop_clave like 'EXAMEN.PALANCA%';
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.PALANCA.PUNTOS','80');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.PALANCA.TIEMPO.DURACION.HASTA','60000');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.PALANCA.ERRORES.PERMITIDOS.HASTA','40');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.PALANCA.ERRORES.TIEMPO','10000');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.PALANCA.ERRORES.PUNTOS.SIN.ACTIVAR.HASTA','15');


--VISION TESTER
--delete from APP.PROPIEDAD where prop_clave like 'EXAMEN.AGUDEZA.VISUAL.PLANTILLA%';
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.AGUDEZA.VISUAL.IMG.BONOCULAR','/images/vision/agudeza visual/binocular.png');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.AGUDEZA.VISUAL.IMG.MONOCULAR.IZQ','/images/vision/agudeza visual/monocular_izq.png');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.AGUDEZA.VISUAL.IMG.MONOCULAR.DER','/images/vision/agudeza visual/monocular_der.png');


--delete from APP.PROPIEDAD where prop_clave like 'EXAMEN.PROFUNDIDAD.VISUAL.PLANTILLA%';
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.PROFUNDIDAD.VISUAL.IMG','/images/vision/profundidad visual/planos.png');


--delete from APP.PROPIEDAD where prop_clave like 'EXAMEN.ENCANDILAMIENTO.PLANTILLA%';
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.ENCANDILAMIENTO.IMG','/images/vision/encandilamiento/encandilamiento.png');

--delete from APP.PROPIEDAD where prop_clave like 'EXAMEN.VISION.NOCTURNA.PLANTILLA%';
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.VISION.NOCTURNA.IMG','/images/vision/vision noctura/visionNocturna.png');

--delete from APP.PROPIEDAD where prop_clave like 'EXAMEN.REC.ENCANDILAMIENTO.IMG%';
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.REC.ENCANDILAMIENTO.IMG','/images/vision/encandilamiento/rec_encandilamiento.png');

--delete from APP.PROPIEDAD where prop_clave like 'EXAMEN.FOTOCRAMATICA.VISUAL.IMG%';
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.FOTOCRAMATICA.VISUAL.IMG','/images/vision/colores/colores.png');

--delete from APP.PROPIEDAD where prop_clave like 'EXAMEN.FORIA.IMG%';
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('EXAMEN.FORIA.IMG','/images/vision/foria/foria.png');