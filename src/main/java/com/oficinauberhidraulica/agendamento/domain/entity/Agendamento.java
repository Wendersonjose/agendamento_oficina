package com.oficinauberhidraulica.agendamento.domain.entity;

import com.oficinauberhidraulica.agendamento.domain.enums.TipoServico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;

    private String telefone;

    private String modeloVeiculo;

    private String placaVeiculo;

    private LocalDateTime dataHoraAgendamento;

    @Enumerated(EnumType.STRING)
    private TipoServico servico;

}
