 update APP.PERSONA
 	set PER_SEXO= CASE WHEN upper(PER_SEXO)='H' THEN '1'
 					   WHEN upper(PER_SEXO)='M' THEN '2'
 					   ELSE PER_SEXO
 				  END,
 	PER_DONANTE= CASE WHEN upper(PER_DONANTE)='SI' THEN 'S'
 					  WHEN upper(PER_DONANTE)='NO' THEN 'N'
 					  ELSE PER_DONANTE  
 				 END,
 	PER_GRUPO_SANGUINEO= CASE WHEN upper(PER_GRUPO_SANGUINEO)='0 +' THEN '1' 
 							  WHEN upper(PER_GRUPO_SANGUINEO)='0 -' THEN '2'
 							  WHEN upper(PER_GRUPO_SANGUINEO)='A +' THEN '3'
 							  WHEN upper(PER_GRUPO_SANGUINEO)='A -' THEN '4'
 							  WHEN upper(PER_GRUPO_SANGUINEO)='AB +' THEN '5'
 							  WHEN upper(PER_GRUPO_SANGUINEO)='AB -' THEN '6'
 							  WHEN upper(PER_GRUPO_SANGUINEO)='B +' THEN '7'
 							  WHEN upper(PER_GRUPO_SANGUINEO)='B -' THEN '8'
 							  ELSE PER_GRUPO_SANGUINEO
 					     END,
 	PER_NACIONALIDAD = (case when (SELECT DOM_CODIGO 
							            	FROM APP.DOMINIO 
							              WHERE DOM_CLAVE= 'Nacionalidad'
							                AND DOM_CODIGO=PER_NACIONALIDAD) is null then 'ARG'
							          else (SELECT DOM_CODIGO 
							            	FROM APP.DOMINIO 
							              WHERE DOM_CLAVE= 'Nacionalidad'
							                AND DOM_CODIGO=PER_NACIONALIDAD) end),
 	PER_TIPO_DOC=(case when (SELECT DOM_CODIGO 
							            	FROM APP.DOMINIO 
							              WHERE DOM_CLAVE= 'Tipo Documento'
							                AND DOM_CODIGO=PER_TIPO_DOC) is null then 'DNI-DNI'
							          else (SELECT DOM_CODIGO 
							            	FROM APP.DOMINIO 
							              WHERE DOM_CLAVE= 'Tipo Documento'
							                AND DOM_CODIGO=PER_TIPO_DOC) end)
 	;