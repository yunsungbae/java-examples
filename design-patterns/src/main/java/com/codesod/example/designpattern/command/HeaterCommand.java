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
package com.codesod.example.designpattern.command;

import com.codesod.example.designpattern.command.receiver.HeaterController;
import com.codesod.example.designpattern.command.receiver.HeaterController.Level;

import java.util.Map;
import java.util.Objects;

abstract class HeaterCommand implements Command {
  final HeaterController heaterController;
  private final Map<Level, ? extends HeaterCommand> heaterCommands;
  private HeaterController.Level previousLevel;

  HeaterCommand(
      HeaterController heaterController,
      Map<Level, ? extends HeaterCommand> heaterCommands
  ) {
    this.heaterController = Objects.requireNonNull(heaterController);
    this.heaterCommands = Objects.requireNonNull(heaterCommands);
    this.previousLevel = heaterController.getCurrentLevel();
  }

  @Override
  public void execute() {
    previousLevel = heaterController.getCurrentLevel();
    changeHeatingLevel();
  }

  @Override
  public void undo() {
    heaterCommands.get(previousLevel)
        .execute();
  }

  abstract void changeHeatingLevel();
}