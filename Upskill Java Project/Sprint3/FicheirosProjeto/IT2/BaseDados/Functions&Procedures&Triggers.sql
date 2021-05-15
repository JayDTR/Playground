-- Função getAreasAtividade

create or replace function getAreasAtividade return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from AreaAtividade;
 return v_ret;
end;
/

-- Função getCompetenciaTecnica
create or replace function getCompetenciaTecnica return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from CompetenciaTecnica;
 return v_ret;
end;
/

-- Função getCategoriaTarefa
create or replace function getCategoriaTarefa return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from CategoriaTarefa;
 return v_ret;
end;
/

-- Função getTarefasOrganizacao
create or replace function getTarefasOrganizacao(nif_org organizacao.nif%type) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select *
 from Tarefa
 where niforganizacao = nif_org;
 return v_ret;
end;
/

-- Função getColaboradoresOrganizacao

create or replace function getColaboradoresOrganizacao(nif_org organizacao.nif%type) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select *
 from Colaborador
 where niforganizacao = nif_org;
 return v_ret;
end;
/

-- Trigger Valida Data do Reconhecimento de competencia tecnica para não poder ser maior que hoje

create or replace trigger trgValidaDataReconhecimentoCT after insert or update on ReconhecimentoCT for each row
begin
 if trunc(:new.dataReconhecimento) >= trunc(sysdate) then
 raise_application_error(-20000, 'Erro na data de realização do Reconhecimento de Competencia Tecnica');
 end if;
end;
/

-- Trigger Valida Datas do Anuncio

create or replace trigger trgValidaDatasAnuncio after insert or update on Anuncio for each row
begin
	if trunc(:new.dtFimPublicacao) <= trunc(:new.dtInicioPublicacao) then
 raise_application_error(-20001, 'Erro! Data de Fim de Publicação não pode ser antes do Inicio da Publicação');
 end if;
 if trunc(:new.dtInicioCandidatura) <= trunc(:new.dtFimPublicacao) then
 raise_application_error(-20001, 'Erro! Data de Inicio de Candidatura não pode ser depois do Fim da Publicação');
 end if;
   if trunc(:new.dtFimCandidatura) <= trunc(:new.dtInicioCandidatura) then
 raise_application_error(-20001, 'Erro! Data de Fim de Candidatura não pode ser antes do Inicio da Candidaturas');
 end if;
 if trunc(:new.dtInicioSeriacao) <= trunc(:new.dtFimCandidatura) then
 raise_application_error(-20001, 'Erro! Data de Inicio de Seriação não pode ser antes da data de Fim de Candidaturas');
 end if;
 if trunc(:new.dtFimSeriacao) <= trunc(:new.dtInicioSeriacao) then
 raise_application_error(-20001, 'Erro! Data de Fim de Seriação não pode ser antes da data de Inicio de Seriação');
 end if;
end;
/

create or replace procedure addAreaAtividade( 
    p_codunico AreaAtividade.Id%type, 
    p_descricao AreaAtividade.descBreve%type, 
    p_descdetailed AreaAtividade.descDetalhada%type) 
    is
    begin 
insert into AreaAtividade(Id, descBreve, descDetalhada) values (p_codunico, p_descricao, p_descdetailed); 
end;
/

create or replace procedure addCandidatura( 
    p_dataCandidatura candidatura.dataCandidatura%type,
    p_valorPretendido candidatura.valorPretendido%type,
    p_nrDias candidatura.nrDias%type,
    p_txtApresentacao candidatura.txtApresentacao%type,
    p_txtMotivacao candidatura.txtMotivacao%type,
   	p_nifFreelancer candidatura.nifFreelancer%type,
   	p_idAnuncio candidatura.idanuncio%type) 
    is
    begin 
insert into Candidatura (dataCandidatura, valorPretendido, nrDias, txtApresentacao, txtMotivacao, nifFreelancer, idAnuncio)
 values (p_dataCandidatura,p_valorPretendido,p_nrDias,p_txtApresentacao,p_txtMotivacao,p_nifFreelancer,p_idAnuncio);
end;
/
