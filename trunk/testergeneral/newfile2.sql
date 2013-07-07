--anda
select *
  from app.PERSONA
 where per_numero_doc = '31097507';--954


select *
  from app.persona_Examen
 where PER_ID = 954;--1073
 
 
 
 --no anda
 select *
  from app.PERSONA
 where per_numero_doc = '25303206';--1280

 select *
  from app.persona_Examen
 where PER_ID = 1280;--4468

 
 select per.*,perexa.*,exa.*,detalleRes.*,detalle.*,
		  (select detalleResE.rde_imagen
		 from  app.persona_examen perexaE,
		 	   app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexaE.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and perexaE.pexa_id = perexa.pexa_id
		    and detalleE.exad_codigo = 'TEST_AUDIO') as GRAFICO_AUDIO,
		    		  (select detalleResE.rde_imagen
		 from  app.persona_examen perexaE,
		 	   app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexaE.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and perexaE.pexa_id = perexa.pexa_id
		    and detalleE.exad_codigo = 'TEST_AUDIO_DER') as GRAFICO_AUDIO_DER,
		  (select detalleResE.rde_imagen
		 from  app.persona_examen perexaE,
		 	   app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexaE.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and perexaE.pexa_id = perexa.pexa_id
		    and detalleE.exad_codigo = 'TEST_AUDIO_IZQ') as GRAFICO_AUDIO_IZQ
  from app.persona per,
              app.persona_examen perexa,
              app.examen exa,
              app.resultado_detalle_examen detalleRes,
	      app.examen_detalle detalle
where perexa.per_id = per.per_id
    and exa.exa_id=perexa.exa_id
    and perexa.pexa_id= detalleRes.pexa_id
    and detalle.exad_id = detalleRes.exad_id
    and perexa.pexa_id = 4468
order by detalle.exad_orden,detalle.exad_id;

select	detalleRes.*,detalle.*/*,
		  (select detalleResE.rde_imagen
		 from  app.persona_examen perexaE,
		 	   app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexaE.pexa_id= detalleResE.pexa_id
		--and detalle.exa_id = exa.exa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and perexaE.pexa_id = perexa.pexa_id
		    and detalleE.exad_codigo = 'TEST_AUDIO') as GRAFICO_AUDIO*/
  from app.persona per,
              app.persona_examen perexa,
              app.examen exa,
              app.resultado_detalle_examen detalleRes,
	      app.examen_detalle detalle
where perexa.per_id = per.per_id
    and exa.exa_id=perexa.exa_id
    and detalle.exa_id = exa.exa_id
    and perexa.pexa_id= detalleRes.pexa_id
    and detalle.exad_id = detalleRes.exad_id
    and perexa.pexa_id = 4468
order by detalle.exad_orden,detalle.exad_id;

select 1
  from app.persona per,
              app.persona_examen perexa,
              app.examen exa,
              app.examen_detalle detalle,
              app.resultado_detalle_examen detalleRes
where perexa.per_id = per.per_id
    and exa.exa_id=perexa.exa_id
    --and detalle.exa_id = exa.exa_id
    
    and perexa.pexa_id= detalleRes.pexa_id
    and detalle.exad_id = detalleRes.exad_id
    
    and perexaE.pexa_id = 4468
--order by detalle.exad_orden,detalle.exad_id
;


select detalleResE.rde_imagen as GRAFICO_AUDIO
		 from  app.persona_examen perexaE,
		 	   app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexaE.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and perexaE.pexa_id = perexa.pexa_id
		    and detalleE.exad_codigo = 'TEST_AUDIO'		    
and perexa.pexa_id = 4468;
		    
   alter table "APP"."RESULTADO_DETALLE_EXAMEN" add constraint "FK_000010" foreign key ("EXAD_ID") references "APP"."EXAMEN_DETALLE"("EXAD_ID");
    alter table "APP"."RESULTADO_DETALLE_EXAMEN" add constraint "FK_00009" foreign key ("PEXA_ID") references "APP"."PERSONA_EXAMEN"("PEXA_ID");

delete from app.persona_examen where pexa_id <> 4468;

delete from app.resultado_detalle_examen where pexa_id <> 4468;

delete from app.RESULTADO;

select count(*)
 from app.persona;
 
 select count(*)
 from app.RESULTADO_DETALLE_EXAMEN;
 
 
  select count(*)
 from app.persona_examen;


select per.*,perexa.*,exa.*,detalleRes.*,detalle.*,
		  (select detalleResE.rde_imagen
		 from  app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexa.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and detalleE.exad_codigo = 'TEST_AUDIO') as GRAFICO_AUDIO,
		  (select detalleResE.rde_imagen
		 from  app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexa.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and detalleE.exad_codigo = 'TEST_AUDIO_DER') as TEST_AUDIO_DER,
		  (select detalleResE.rde_imagen
		 from  app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexa.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and detalleE.exad_codigo = 'TEST_AUDIO_IZQ') as TEST_AUDIO_IZQ
  from app.persona per,
              app.persona_examen perexa,
              app.examen exa,
              app.resultado_detalle_examen detalleRes,
	      app.examen_detalle detalle
where perexa.per_id = per.per_id
    and exa.exa_id=perexa.exa_id
and detalle.exa_id = exa.exa_id
    and perexa.pexa_id= detalleRes.pexa_id
    and detalle.exad_id = detalleRes.exad_id
    and perexa.pexa_id = 4468
order by detalle.exad_orden,detalle.exad_id;


    --from app.resultado_detalle_examen where pexa_id = 4468;
    
	--create unique index "SQL100414203432130" on "APP"."RESULTADO_DETALLE_EXAMEN"("RDE_ID");
    create index "SQL100414204041030" on "APP"."RESULTADO_DETALLE_EXAMEN"("PEXA_ID");
    drop index app.SQL100414204041030;

select 1
from "APP"."RESULTADO_DETALLE_EXAMEN" where PEXA_ID = 4468;

select per.*,perexa.*,exa.*,detalleRes.*,detalle.*,
		  (select detalleResE.rde_imagen
		 from  app.resultado_detalle_examen detalleResE,
			   app.examen_detalle detalleE
		where perexa.pexa_id= detalleResE.pexa_id
		    and detalleE.exad_id = detalleResE.exad_id
		    and detalleE.exad_codigo = 'TEST_AUDIO'
) as GRAFICO_AUDIO
  from app.persona per,
              app.persona_examen perexa,
              app.examen exa,
              app.resultado_detalle_examen detalleRes,
	      app.examen_detalle detalle
where perexa.per_id = per.per_id
    and exa.exa_id=perexa.exa_id
and detalle.exa_id = exa.exa_id
    and perexa.pexa_id= detalleRes.pexa_id
    and detalle.exad_id = detalleRes.exad_id
    and perexa.pexa_id = $P{p_pexa_id}
order by detalle.exad_orden,detalle.exad_id