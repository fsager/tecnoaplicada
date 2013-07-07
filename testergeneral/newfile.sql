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
    and perexa.pexa_id = $P{p_pexa_id}
order by detalle.exad_orden,detalle.exad_id;


select * from app.persona_examen order by pexa_id desc;
