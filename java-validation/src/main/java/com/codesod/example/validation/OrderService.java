/*
 * Copyright 2017 MD Sayem Ahmed
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codesod.example.validation;

import com.codesod.example.validation.rule.OrderItemValidator;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
class OrderService {
  private final MenuRepository menuRepository;
  private final OrderItemValidator validator;

  OrderService(MenuRepository menuRepository, OrderItemValidator orderItemValidator) {
    this.menuRepository = menuRepository;
    this.validator = orderItemValidator;
  }

  void createOrder(OrderDTO orderDTO) {
    orderDTO.getOrderItems()
        .forEach(validator::validate);

    menuRepository.save(orderDTO);
  }
}
