
package com.oficinauberhidraulica.agendamento.repository;

import com.oficinauberhidraulica.agendamento.domain.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AgendamentoRepository
        extends JpaRepository<Agendamento, Long> {

    boolean existsByPlacaVeiculoAndDataHoraAgendamentoGreaterThanEqualAndDataHoraAgendamentoLessThan(
            String placaVeiculo,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    @Query("""
        SELECT COUNT(DISTINCT a.placaVeiculo)
        FROM Agendamento a
        WHERE a.dataHoraAgendamento >= :inicio
        AND a.dataHoraAgendamento < :fim
    """)
    long contarVeiculosPorDia(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );
}
