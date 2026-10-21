
package com.oficinauberhidraulica.agendamento.services;

import com.oficinauberhidraulica.agendamento.domain.entity.Agendamento;
import com.oficinauberhidraulica.agendamento.repository.AgendamentoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Agendamento save(Agendamento agendamento) {

        LocalDate data = agendamento
                .getDataHoraAgendamento()
                .toLocalDate();

        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.plusDays(1).atStartOfDay();

        boolean placaJaAgendada = repository
                .existsByPlacaVeiculoAndDataHoraAgendamentoGreaterThanEqualAndDataHoraAgendamentoLessThan(
                        agendamento.getPlacaVeiculo(),
                        inicio,
                        fim
                );

        if (!placaJaAgendada) {

            long quantidade = repository.contarVeiculosPorDia(
                    inicio,
                    fim
            );

            if (quantidade >= 3) {
                throw new IllegalStateException(
                        "Limite de 3 veículos por dia atingido."
                );
            }
        }

        return repository.save(agendamento);
    }
}
