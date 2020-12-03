package it.esdebitamiretake.retake_ticket.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;

import it.esdebitamiretake.retake_ticket.fe.TemplateInfo;

public abstract interface TemplateCustomRepository
{
  public abstract List<TemplateInfo> findTemplatesInfo(Pageable paramPageable);
}


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\db\repo\TemplateCustomRepository.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */