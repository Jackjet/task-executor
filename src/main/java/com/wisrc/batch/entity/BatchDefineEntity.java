package com.wisrc.batch.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Data
@Table(name = "dispatch_batch_define")
@Entity
public class BatchDefineEntity {

    @Id
    @Column(name = "batch_id")
    private String batchId;

    @Column(name = "code_number")
    private String codeNumber;

    @Column(name = "batch_desc")
    private String batchDesc;

    @Column(name = "batch_status")
    private String batchStatus;

    @Column(name = "as_of_date")
    private String asOfDate;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "ret_msg")
    private String retMsg;

    @Column(name = "complete_date")
    private String completeDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "domain_id")
    private String domainId;

    @Column(name = "pagging_freq")
    private String paggingFreq;

    @Column(name = "pagging_freq_mult")
    private String paggingFreqMult;


}
