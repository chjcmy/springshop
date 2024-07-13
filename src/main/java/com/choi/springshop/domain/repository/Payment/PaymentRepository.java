package com.choi.springshop.domain.repository.Payment;

import com.choi.springshop.domain.model.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payments, Long> {
}
