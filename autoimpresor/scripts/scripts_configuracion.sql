--select * from APP.Licencia;


--LICENCE PROPERTIES
--SUBIR AL UPDATE m�s el jar de tecnolog�applicada.jar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SUBIR AL UPDATE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SUBIR AL UPDATE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SUBIR AL UPDATE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SUBIR AL UPDATE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SUBIR AL UPDATE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
INSERT INTO APP.PROPIEDAD VALUES ('VALIDAR_LICENCIA','S',null);
INSERT INTO APP.PROPIEDAD VALUES ('LICENCIA_VITALICIA','N',null);
INSERT INTO APP.PROPIEDAD VALUES ('LICENCIA.CANTIDAD.INTENTOS.PERMITIDOS','5',null);
INSERT INTO APP.PROPIEDAD VALUES ('LICENCIA.FECHA.ULTIMO.INTENTO','',null);
INSERT INTO APP.PROPIEDAD VALUES ('LICENCIA.CANTIDAD.INTENTOS','0',null);

INSERT INTO APP.PROPIEDAD VALUES ('PERIODO.PRUEBA.FECHA.INICIO','',null);
INSERT INTO APP.PROPIEDAD VALUES ('LICENCE.LAST.DATE.ACTIVATED','',null);
INSERT INTO APP.PROPIEDAD VALUES ('LICENCE.NRO','',null);
INSERT INTO APP.PROPIEDAD VALUES ('LICENCED','N',null);
INSERT INTO APP.PROPIEDAD VALUES ('PERIODO.PRUEBA.DURACION','45',null);
--LICENCE PROPERTIES

INSERT INTO APP.PROPIEDAD VALUES ('LIC_MARGEN_IZQ','1',null);
INSERT INTO APP.PROPIEDAD VALUES ('LIC_MARGEN_SUP','2',null);
INSERT INTO APP.PROPIEDAD VALUES ('LIC_DESPLAZAMIENTO_TRAS','0',null);
INSERT INTO APP.PROPIEDAD VALUES ('DEFAULT_PRINTER','',null);


insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (1,'A-1',36,16,99,'Ciclomotores cuya cilindrada no supere los cin','Ciclomotores cuya cilindrada no supere los cincuenta cc. (50 cc.).',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (2,'A-2',36,18,99,'Motocicletas y triciclos cuya cilindrada super','Motocicletas y triciclos cuya cilindrada supere los 50 cc y no exceda los 150 cc. Los vehiculos autorizados por A-1.',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (3,'A-3',36,18,99,'Motocicletas de m�s de 150 cc, adem�s de los a','Motocicletas de m�s de 150 cc, adem�s de los autorizados en la clase A-2.',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (4,'B-1',36,18,99,'Autom�viles, camionetas y casas rodantes motor','Autom�viles, camionetas y casas rodantes motorizadas cuyo peso no exceda los 3500 kg y el n�mero de plazas no sea superior a nueve.',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (5,'B-2',36,18,99,'Automoviles y camionetas que arrastren remolqu','Automoviles y camionetas que arrastren remolques de hasta 750 kg, y cuyo peso total no supere los 3500 kg.Vehiculos autorizados en la clase B-1.',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (6,'C',36,21,65,'Camiones sin acoplado ni semiacoplado y casas ','Camiones sin acoplado ni semiacoplado y casas rodantes motorizadas cuyo peso exceda los 3500 kg.Veh�culos autorizados por la clase B-2.',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (7,'D-1',36,21,65,'Transportes de pasajeros de hasta ocho plazas ','Transportes de pasajeros de hasta ocho plazas excluido el conductor.Vehiculos autorizados con B-1.',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (8,'D-2',36,21,65,'Transportes de pasajeros con m�s de ocho plaza','Transportes de pasajeros con m�s de ocho plazas excluido el conductor.Vehiculos autorizados con B-2 y D-1.',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (9,'D-3',36,21,65,'Vehiculos destinados a los servicios de polici','Vehiculos destinados a los servicios de policia, bomberos y asist. sanitaria con un peso m�x. de 3500 kg.Vehiculos autorizados con D-1.',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (10,'E-1',36,21,70,'Camiones cualquiera sea su peso.Vehiculos ar','Camiones cualquiera sea su peso.Vehiculos articulados o con acoplado.Vehiculos autorizados con clase C.',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (11,'E-2',36,21,65,'Maquinaria especial no agricola.Vehiculos au','Maquinaria especial no agricola.Vehiculos autorizados con clase C.',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (12,'F',36,18,99,'Veh�culos con adaptaciones  para discapacidad.','Veh�culos con adaptaciones  para discapacidad.',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (13,'G',36,18,99,'Tractores y maquinaria especial agr�cola.','Tractores y maquinaria especial agr�cola.',0,0,0,0,0,0);
insert into APP.CLASE_LICENCIA (CLL_ID,CLL_NOMBRE_CLASE,CLL_VIGENCIA_PREDETERMINADA,CLL_EDAD_MINIMA,CLL_EDAD_MAXIMA,CLL_DESCRIPCION_CORTA,CLL_DESCRIPCION,CLL_IMPORTEX6MESES,CLL_IMPORTEX12MESES,CLL_IMPORTEX24MESES,CLL_IMPORTEX36MESES,CLL_IMPORTEX48MESES,CLL_IMPORTEX60MESES)  values (14,'D-4',24,21,65,'Vehiculos destinados a los servicios de Polici','Vehiculos destinados a los servicios de Policia, Bomberos y Asistencia Sanitaria con un peso mayor a 3.500 kg.',0,0,0,0,0,0);


insert into APP.ANTECEDENTE (ANT_ID,ANT_DESCRIPCION,ANT_APRUEBA_SN) values (1,'Falta','N');
insert into APP.ANTECEDENTE (ANT_ID,ANT_DESCRIPCION,ANT_APRUEBA_SN) values (2,'Sin antecedentes','S');
insert into APP.ANTECEDENTE (ANT_ID,ANT_DESCRIPCION,ANT_APRUEBA_SN) values (3,'Con antecedentes no graves','S');
insert into APP.ANTECEDENTE (ANT_ID,ANT_DESCRIPCION,ANT_APRUEBA_SN) values (4,'Con antecedentes graves','S');
insert into APP.ANTECEDENTE (ANT_ID,ANT_DESCRIPCION,ANT_APRUEBA_SN) values (5,'Con suspension de la licencia','N');
insert into APP.ANTECEDENTE (ANT_ID,ANT_DESCRIPCION,ANT_APRUEBA_SN) values (6,'Con inhabilitacion temporal','N');
insert into APP.ANTECEDENTE (ANT_ID,ANT_DESCRIPCION,ANT_APRUEBA_SN) values (7,'Con inhabilitacion permanente','N');
insert into APP.ANTECEDENTE (ANT_ID,ANT_DESCRIPCION,ANT_APRUEBA_SN) values (8,'Con suspenso o inhabilitacion en sede judicial','N');




INSERT INTO APP.USUARIO values(-1,'Administrador Sistema','Administrador del Sistema','1',null,'S','N','S','S','S','S','S','N','S','N',NULL);


INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('GRAL','Tipo Documento','DNI','DNI','DNI');    
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Tipo Documento','L.C','L.C','L.C');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Tipo Documento','L.E','L.E','L.E');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Tipo Documento','C.I','C.I','C.I');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Tipo Documento','Pasap','Pasap','Pasap');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Sexo','H','MASCULINO','MASCULINO');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Sexo','M','FEMENINO','FEMENINO');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Donante','SI','SI','SI');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Donante','NO','NO','SI');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Argentino','Argentino','Argentino');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Argentina','Argentina','Argentina');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Espa�ola','Espa�ola','Espa�ola');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Espa�ol','Espa�ol','Espa�ol');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Boliviana','Boliviana','Boliviana');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Boliviano','Boliviano','Boliviano');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Estadounidense','Estadounidense','Estadounidense');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Chilena','Chilena','Chilena');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Chileno','Chileno','Chileno');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Salvadore�a','Salvadore�a','Salvadore�a');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Salvadore�o','Salvadore�o','Salvadore�o');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Croata','Croata','Croata');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Irlandes','Irlandes','Irlandes');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Irlandesa','Irlandesa','Irlandesa');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Italiana','Italiana','Italiana');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Nacionalidad','Italiano','Italiano','Italiano');


INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Estado Civil','CASADO','CASADO','CASADO');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Estado Civil','SOLTERO','SOLTERO','SOLTERO');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Estado Civil','SEPARADO','SEPARADO','SEPARADO');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Estado Civil','VIUDO','VIUDO','VIUDO');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','0 -','0 -','0 -');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','0 +','0 +','0 +');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','A -','A -','A -');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','A +','A +','A +');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','B -','B -','B -');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','B +','B +','B +');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','AB -','AB -','AB -');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('GRAL','Grupo Sangu�neo','AB +','AB +','AB +');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('RESTRICCION','Restricci�n Auditiva','Operaci�n Auditiva','Operaci�n Auditiva','Operaci�n Auditiva');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION)  VALUES ('RESTRICCION','Restricci�n Auditiva','Usa Aud�fonos','Usa Aud�fonos','Usa Aud�fonos');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('RESTRICCION','Restricci�n F�sica','Pr�tesis Superior','Pr�tesis Superior','Pr�tesis Superior');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('RESTRICCION','Restricci�n F�sica','Pr�tesis Inferior','Pr�tesis Inferior','Pr�tesis Inferior');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('RESTRICCION','Restricci�n F�sica','Afecci�n Miembros Superiores','Afecci�n Miembros Superiores','Afecci�n Miembros Superiores');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('RESTRICCION','Restricci�n F�sica','Afecci�n Miembros Inferiores','Afecci�n Miembros Inferiores','Afecci�n Miembros Inferiores');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('IMAGENES','EXTENCION_IMAGENES','png','png','png');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('IMAGENES','EXTENCION_IMAGENES','jpg','jpg','jpg');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('IMAGENES','EXTENCION_IMAGENES','jpeg','jpeg','jpeg');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('IMAGENES','EXTENCION_IMAGENES','gif','gif','gif');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('IMAGENES','EXTENCION_IMAGENES','tif','tif','tif');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('IMAGENES','EXTENCION_IMAGENES','tiff','tiff','tiff');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('EXAMEN','EXAMEN_ESTADO','Aprobado','Aprobado','Aprobado');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('EXAMEN','EXAMEN_ESTADO','Exento','Exento','Exento');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('EXAMEN','EXAMEN_ESTADO','Falta','Falta','Falta');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('EXAMEN','EXAMEN_ESTADO','Reprobado','Reprobado','Reprobado');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('EXAMEN','EXAMEN_MEDICO_ESTADO','Apto','Apto','Apto');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('EXAMEN','EXAMEN_MEDICO_ESTADO','Apto con reservas','Apto con reservas','Apto con reservas');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('EXAMEN','EXAMEN_MEDICO_ESTADO','Falta','Falta','Falta');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('EXAMEN','EXAMEN_MEDICO_ESTADO','No apto','No apto','No apto');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('LICENCIA','TIPO_TRAMITE','Renovaci�n','Renovaci�n','Renovaci�n');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('LICENCIA','TIPO_TRAMITE','Primer licencia','Primer licencia','Primer licencia');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('LICENCIA','TIPO_TRAMITE','Modificada','Modificada','Modificada');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('LICENCIA','TIPO_TRAMITE','Aprendizaje','Aprendizaje','Aprendizaje');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('LICENCIA','TIPO_TRAMITE','Duplicado','Duplicado','Duplicado');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('LICENCIA','TIPO_TRAMITE','Pres. lic. ant.','Pres. lic. ant.','Presenta licencia anterior');

INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('LICENCIA','DURACIONES_PREDETERMINADAS_LICENCIA','06','06','06');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('LICENCIA','DURACIONES_PREDETERMINADAS_LICENCIA','12','12','12');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('LICENCIA','DURACIONES_PREDETERMINADAS_LICENCIA','24','24','24');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('LICENCIA','DURACIONES_PREDETERMINADAS_LICENCIA','36','36','36');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('LICENCIA','DURACIONES_PREDETERMINADAS_LICENCIA','48','48','48');
INSERT INTO APP.DOMINIO (DOM_TIPO,DOM_CLAVE,DOM_CODIGO,DOM_VALOR_MOSTRAR,DOM_DESCRIPCION) VALUES ('LICENCIA','DURACIONES_PREDETERMINADAS_LICENCIA','60','60','60');

INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.UBICACION.LICENCIAS.IMPORTAR','S');

INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('FORMATO.FECHA','dd-MM-yyyy');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('FORMATO.HORA','HH:mm:ss');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('FORMATO.FECHA.HORA','dd-MM-yyyy HH:mm:ss');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('DIRECTORIO.IMAGENES','C:\\');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('DIRECTORIO.BACKUP.PRIMARIO','D:\\');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('DIRECTORIO.BACKUP.SECUNDARIO','C:\\');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('DIRECTORIO.IMAGEN.FONDO.APLICACION','C:\\');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('PERSONA.FOTO.REQUERIDA','S');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('PERSONA.FIRMA.REQUERIDA','N');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('PERSONA.DNI.MASCARA','##.###.###');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('PERSONA.DNI.PERMITIR.SOLO.NUMEROS','S');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('PERSONA.DNI.FIJAR.CANTIDAD.CARACTERES','S');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('PERSONA.DNI.CANTIDAD.CARACTERES.FIJA','8');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.ACTUALIZACION.AUTOMATICA.INICIO','N');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.DEVICES.CAMARA','vfw:Microsoft WDM Image Capture (Win32):0');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.GUARDAR.LOG.EVENTOS','S');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.CONSERVAR.DATOS.LOG.EVENTOS.XDIAS','20');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.ORIGEN.FOTOS','DISCO');--DISCO WEBCAM EXISTE ENUM
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.ORIGEN.FIRMAS','DISCO');--DISCO PAD EXISTE ENUM
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.IMAGENES.PORCENTAJE.REDUCCION','25');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FIRMAS.PORCENTAJE.REDUCCION','25');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.BACKUP.AUTOMATICO.ACTIVADO','N');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.BACKUP.AUTOMATICO.CADAXDIAS','20');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.BACKUP.SECUNDARIO.SI-NO','N');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.BACKUP.FECHAULTIMOBACKUP','28-04-2010');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.EMAIL.AUTOMATICO.IMPRESION.LICENCIAS','S');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.EMAIL.CENTRO.IMPRESION.LICENCIAS','jtesta@comininet.com.ar');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.NOMBRE.MUNICIPIO','Berrotar�n');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.CODIGO.MUNICIPIO','290');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.IMAGEN.PRIMARIA','');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FOTO.MUNICIPIO','');
INSERT INTO APP.PROPIEDAD (PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.MUNICIPIO.ES_CENTRO_IMPRESOR_S_N','N');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FTP_PRINCIPAL_URL','DXTEn5m2NtfIb0JrQ88tOwkSptl3lH87AJZPfePBh80=');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FTP_PRINCIPAL_USER','5WaKymtinfeyUOiInZ0rvp1c1K4M0JAu34k1rvKt5uI');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FTP_PRINCIPAL_PASSWORD','uLBg40t0YiQ9/9dBV8UgSg==');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FTP_SECUNDARIO_URL','1yT7xdyXFOC1p1EecQ0V3Q==');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FTP_SECUNDARIO_USER','sva1t1RMRadkMMoiRlpKWA==');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.FTP_SECUNDARIO_PASSWORD','WXr7F2JirExUiUzOnSAeyA==');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.NOMBRE.PROGRAMA','Autoimpresor');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.CODIGO_REGION_DESTINO','ar');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.VERSION_PRINCIPAL','v1');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.VALIDAR_REQUISITOS_SN','S');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.HASH_ULTIMO_ARCHIVO_SCRIPT_EJECUTADO','0');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('SISTEMA.ULTIMO.FIRMANTE','');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('MUNICIPIO.RANGO.LICENCIAS.DESDE','1000');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('MUNICIPIO.RANGO.LICENCIAS.HASTA','2000');
INSERT INTO APP.PROPIEDAD(PROP_CLAVE,PROP_VALOR) VALUES ('MUNICIPIO.RANGO.LICENCIAS.AVISAR.CUANDO.FALTAN','100');


