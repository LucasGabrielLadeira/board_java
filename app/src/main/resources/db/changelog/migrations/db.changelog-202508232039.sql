--liquibase formatted sql
--changeset lucas:202508232039
--comment: blocks table create

CREATE TABLE BLOCKS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    blocked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    blocked_reason VARCHAR(255) NOT NULL,
    unblocked_at TIMESTAMP NULL,
    unblock_reason VARCHAR(255) NOT NULL,
    card_id BIGINT NOT NULL,
    CONSTRAINT cards__blocks_fk FOREIGN KEY (card_id) REFERENCES CARDS(id) ON DELETE CASCADE
) ENGINE = InnoDB;


--rollback DROP TABLE BLOCKS