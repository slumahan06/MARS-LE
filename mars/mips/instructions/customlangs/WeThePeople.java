package mars.mips.instructions.customlangs;
import mars.simulator.*;
import mars.mips.hardware.*;
import mars.mips.instructions.syscalls.*;
import mars.*;
import mars.util.*;
import java.util.*;
import java.io.*;
import mars.mips.instructions.*;
import java.util.Random;


public class WeThePeople extends CustomAssembly{
    @Override
    public String getName(){
        return "We the People";
    }

    @Override
    public String getDescription(){
        return "Code MIPS Assembly through U.S. history and government!";
    }

    @Override
    protected void populate() {
        // put (same as addi)
        instructionList.add(
                new BasicInstruction("revolt $t0,$t1",
                "Revolt: set the number of states in ($t0) to 13 and stability in ($t1) to 50",
                BasicInstructionFormat.R_FORMAT,
                "000011 fffff sssss 00000 00000 010101",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();

                        RegisterFile.updateRegister(operands[0], 13);
                        RegisterFile.updateRegister(operands[1], 50);

                        SystemIO.printString("Declaring our independence! 13 states and 50 stability created.\n");
                    }
                }));
        instructionList.add(
                new BasicInstruction("state $t0",
                        "Statehood: increase the number of states in ($t0) by 1",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 fffff 00000 00000 00000 010101",
                        new SimulationCode()
                        {
                            public void simulate(ProgramStatement statement) throws ProcessingException
                            {
                                int[] operands = statement.getOperands();
                                int numberOfStates = RegisterFile.getValue(operands[0]);
                                int newStates = numberOfStates + 1;

                                RegisterFile.updateRegister(operands[0], newStates);
                                SystemIO.printString("New state added to the Union!\n");
                            }
                        }));
        instructionList.add(
                new BasicInstruction("cwar $t0",
                        "Civil War: divide the number of states in ($t0) by 2",
                        BasicInstructionFormat.R_FORMAT,
                        "100000 fffff 00000 00000 00000 100000",
                        new SimulationCode()
                        {
                            public void simulate(ProgramStatement statement) throws ProcessingException
                            {
                                int[] operands = statement.getOperands();
                                int numberOfStates = RegisterFile.getValue(operands[0]);
                                int newStates = numberOfStates / 2;

                                RegisterFile.updateRegister(operands[0], newStates);
                                SystemIO.printString("Civil War! The amount of states is cut in half.\n");
                            }
                        }));
        instructionList.add(
                new BasicInstruction("lpur $t0",
                        "Louisiana Purchase: double the amount of land in ($t0)",
                        BasicInstructionFormat.R_FORMAT,
                        "100000 fffff 00000 00000 00000 010101",
                        new SimulationCode()
                        {
                            public void simulate(ProgramStatement statement) throws ProcessingException
                            {
                                int[] operands = statement.getOperands();
                                int numberOfStates = RegisterFile.getValue(operands[0]);
                                int newStates = numberOfStates * 2;

                                RegisterFile.updateRegister(operands[0], newStates);
                                SystemIO.printString("Completed the Louisiana Purchase!\n");
                            }
                        }));
        instructionList.add(
                new BasicInstruction("factory $t0",
                        "Factory output: increase the GDP of the nation in ($t0) by 1000",
                        BasicInstructionFormat.R_FORMAT,
                        "000000 fffff 00000 00000 00000 010110",
                        new SimulationCode()
                        {
                            public void simulate(ProgramStatement statement) throws ProcessingException
                            {
                                int[] operands = statement.getOperands();
                                int currentGDP = RegisterFile.getValue(operands[0]);
                                int newGDP = currentGDP + 1000;

                                RegisterFile.updateRegister(operands[0], newGDP);
                                SystemIO.printString("Created a new factory!\n");
                            }
                        }));
        instructionList.add(
                new BasicInstruction("moon $t0, $t1",
                        "Send a colony to the moon: increase the number of states in ($t0) by 1 and stability in ($t2) by 10",
                        BasicInstructionFormat.R_FORMAT,
                        "000011 fffff sssss 00000 00000 010110",
                        new SimulationCode()
                        {
                            public void simulate(ProgramStatement statement) throws ProcessingException
                            {
                                int[] operands = statement.getOperands();
                                int numberOfStates = RegisterFile.getValue(operands[0]);
                                int newStates = numberOfStates + 1;

                                int currentStability = RegisterFile.getValue(operands[1]);
                                int newStability = currentStability + 10;

                                RegisterFile.updateRegister(19, currentStability);
                                RegisterFile.updateRegister(operands[0], newStates);
                                RegisterFile.updateRegister(operands[1], newStability);
                                SystemIO.printString("Created a new state on the Moon!\n");
                            }
                        }));
        instructionList.add(
                new BasicInstruction("gov $t0, $t1, $t2", // Realised that my Mod 1 for this function did not have registers; fixed that here
                        "Government info: print out which parties control the Presidency ($t0), Senate ($t1), and House ($t2)",
                        BasicInstructionFormat.R_FORMAT,
                        "111111 fffff sssss ttttt 00000 111111",
                        new SimulationCode()
                        {
                            public void simulate(ProgramStatement statement) throws ProcessingException
                            {
                                int[] operands = statement.getOperands();
                                int teamOneEVCount = RegisterFile.getValue(operands[0]);
                                int senateControl = RegisterFile.getValue(operands[1]);
                                int houseControl = RegisterFile.getValue(operands[2]);

                                SystemIO.printString("The party that controls the Presidency is ");
                                if (teamOneEVCount > 270) {
                                    SystemIO.printString("Team One!\n");
                                } else {
                                    SystemIO.printString("Team Two!\n");
                                }

                                SystemIO.printString("The party that controls the Senate is ");
                                if (senateControl == 1) {
                                    SystemIO.printString("Team One!\n");
                                } else {
                                    SystemIO.printString("Team Two!\n");
                                }

                                SystemIO.printString("The party that controls the House is ");
                                if (houseControl == 1) {
                                    SystemIO.printString("Team One!\n");
                                } else {
                                    SystemIO.printString("Team Two!\n");
                                }
                            }
                        }));
        instructionList.add(
                new BasicInstruction("leg $t0",
                "Legislation: increase the stability of the nation in ($t0) by 5",
                BasicInstructionFormat.R_FORMAT,
                "000000 fffff 00000 00000 00000 010111",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        int currentStability = RegisterFile.getValue(operands[0]);
                        int newStability = currentStability + 5;

                        RegisterFile.updateRegister(19, currentStability);
                        RegisterFile.updateRegister(operands[0], newStability);
                        SystemIO.printString("Popular legislation implemented!\n");
                    }
                }));
        instructionList.add(
                new BasicInstruction("corr $t0",
                "Corruption: subtract 15 from the stability of the nation in ($t0)",
                BasicInstructionFormat.R_FORMAT,
                "000000 fffff 00000 00000 00000 100000",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        int currentStability = RegisterFile.getValue(operands[0]);
                        int newStability = currentStability - 15;

                        RegisterFile.updateRegister(19, currentStability);
                        RegisterFile.updateRegister(operands[0], newStability);
                        SystemIO.printString("Corruption in government?\n");
                    }
                }));
        instructionList.add(
                new BasicInstruction("pun $t0, label",
                "Perfect Union of 50 States: branch to a statement at a label's address if ($t0) is equal to 50",
                BasicInstructionFormat.I_BRANCH_FORMAT,
                "000110 fffff 00001 ssssssssssssssss",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        if (RegisterFile.getValue(operands[0]) == 50) {
                            Globals.instructionSet.processBranch(operands[1]);
                            SystemIO.printString("Fifty states, perfect! Jumping ahead.\n");
                        }
                    }
                }));
        instructionList.add(
                new BasicInstruction("nun $t0, $t1, label",
                "No more Union: branch to a statement at a label's address if ($t0) or ($t1) is less than or equal to 0",
                BasicInstructionFormat.I_BRANCH_FORMAT,
                "000110 fffff sssss tttttttttttttttt",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        if (RegisterFile.getValue(operands[0]) <= 0 || RegisterFile.getValue(operands[1]) <= 0) {
                            Globals.instructionSet.processBranch(operands[2]);
                            SystemIO.printString("No more states or stability is zero! Jumping ahead.\n");
                        }
                    }
                }));

        instructionList.add(
                new BasicInstruction("pelect $t0, $t1",
                "Presidential Election: randomize two party's electoral vote counts in ($t0) and ($t1)",
                BasicInstructionFormat.R_FORMAT,
                "000111 fffff sssss 00000 00000 000000",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        Random random = new Random();
                        int roll = random.nextInt(539); // 0 to 538 electoral votes possible

                        // Add old values to the saved registers
                        int oldTeamOneEV = RegisterFile.getValue(operands[0]);
                        int oldTeamTwoEV = RegisterFile.getValue(operands[1]);
                        RegisterFile.updateRegister(17, oldTeamOneEV);
                        RegisterFile.updateRegister(18, oldTeamTwoEV);

                        // Update the new EV counts in the temporary registers
                        RegisterFile.updateRegister(operands[0], roll);
                        RegisterFile.updateRegister(operands[1], 538 - roll);

                        if (roll > (538-roll)) {
                            SystemIO.printString("Team One has won the Presidential Election!\n");
                        } else {
                            SystemIO.printString("Team Two has won the Presidential Election!\n");
                        }
                    }
                }));

        instructionList.add(
                new BasicInstruction("melect $t0, $t1",
                "Midterm Election: randomize a value of either 1 or 2 in both the House ($t0) and Senate ($t1)",
                BasicInstructionFormat.R_FORMAT,
                "000111 fffff sssss 00000 00000 000001",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        Random random = new Random();
                        int houseRoll = random.nextInt(2) + 1; // 1 = TeamOne, 2 = TeamTwo
                        int senateRoll = random.nextInt(2) + 1;

                        RegisterFile.updateRegister(operands[0], houseRoll);
                        RegisterFile.updateRegister(operands[1], senateRoll);
                        SystemIO.printString("Midterm elections completed!\n");
                    }
                }));

        instructionList.add(
                new BasicInstruction("mlaw $t0, $t1",
                "Martial law: multiply stability in ($t0) by 0.75. Flip a coin to see if rebels secede from land ($t1) and appear in ($s0)",
                BasicInstructionFormat.R_FORMAT,
                "100000 fffff sssss 00000 00000 010111",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        int currentStability = RegisterFile.getValue(operands[0]);
                        int newStability = (int)(currentStability * 0.75);

                        RegisterFile.updateRegister(19, currentStability);
                        RegisterFile.updateRegister(operands[0], newStability);

                        Random random = new Random();
                        int coinFlip = random.nextInt(2);
                        SystemIO.printString("Martial law declared!\n");

                        if (coinFlip == 1) {
                            int numberOfStates = RegisterFile.getValue(operands[1]);
                            int numberOfRebels = RegisterFile.getValue(16);
                            RegisterFile.updateRegister(operands[1], numberOfStates - 2);
                            RegisterFile.updateRegister(16, numberOfRebels + 2);
                            SystemIO.printString("Two states have seceded in response!\n");
                        }
                    }
                }));

        instructionList.add(
                new BasicInstruction("fight $t0, $t1",
                "Fight rebels: check is ($s0) is greater than 0. Flip a coin to see if rebels become part of the land in ($t0) or if stability in ($t1) drops",
                BasicInstructionFormat.R_FORMAT,
                "000111 fffff sssss 00000 00000 000010",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        int numberOfStates = RegisterFile.getValue(operands[0]);
                        int currentStability = RegisterFile.getValue(operands[1]);
                        int numberOfRebels = RegisterFile.getValue(16);

                        if (numberOfRebels > 0) {
                            Random random = new Random();
                            int coinFlip = random.nextInt(2);

                            if (coinFlip == 0) {
                                RegisterFile.updateRegister(operands[1], numberOfStates + numberOfRebels);
                                RegisterFile.updateRegister(16, 0);
                                SystemIO.printString("Rebels reintegrated into the union!\n");
                            } else if (coinFlip == 1) {
                                RegisterFile.updateRegister(19, currentStability);
                                RegisterFile.updateRegister(operands[1], currentStability - 15);
                                SystemIO.printString("Rebels hold their ground! Stability drops!\n");
                            }
                        } else {
                            SystemIO.printString("There are no rebels to fight!\n");
                        }
                    }
                }));

        instructionList.add(
                new BasicInstruction("econ $t0",
                "Economic plan: flip a coin to see if GDP in ($t0) goes up or down significantly",
                BasicInstructionFormat.R_FORMAT,
                "000111 fffff 00000 00000 00000 000011",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        int currentGDP = RegisterFile.getValue(operands[0]);

                        Random random = new Random();
                        int coinFlip = random.nextInt(5); // 0 - 2: Success, 3-4: Fail

                        if (coinFlip <= 2) {
                            RegisterFile.updateRegister(operands[0], currentGDP * 2);
                            SystemIO.printString("Economy flourishes!\n");
                        } else {
                            RegisterFile.updateRegister(operands[0], currentGDP / 2);
                            SystemIO.printString("Economic downturn!\n");
                        }
                    }
                }));

        instructionList.add(
                new BasicInstruction("actpres $t0, $t1, -100",
                "Acting president: give the VP power over the nation for n days. Stability ($t0) and GDP ($t1) will fluctuate in that time period",
                BasicInstructionFormat.I_FORMAT,
                "000111 fffff sssss tttttttttttttttt",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        int currentStability = RegisterFile.getValue(operands[0]);
                        int currentGDP = RegisterFile.getValue(operands[1]);
                        int days = operands[2] << 16 >> 16;

                        Random random = new Random();
                        RegisterFile.updateRegister(19, currentStability);

                        if (days > 0) {
                            for (int i = 0; i < days; i++) {
                                int nextStability = currentStability + random.nextInt(11) - 5; // Values between -5 and 5
                                int nextGDP = currentGDP + random.nextInt(11) - 5; // Values between -5 and 5

                                RegisterFile.updateRegister(operands[0], nextStability);
                                RegisterFile.updateRegister(operands[1], nextGDP);

                                currentStability = nextStability;
                                currentGDP = nextGDP;
                            }
                            SystemIO.printString("Vice President takes control temporarily! New stability is " + currentStability + " and new GDP is " + currentGDP + "\n");
                        } else {
                            SystemIO.printString("Number of days has to be greater than 0!\n");
                            return;
                        }
                    }
                }));

        instructionList.add(
                new BasicInstruction("switch $t0, $t1",
                "Switch parties: switch the parties in control of the House ($t0) and Senate ($t1)",
                BasicInstructionFormat.R_FORMAT,
                "000111 fffff sssss 00000 00000 000100",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        int houseControl = RegisterFile.getValue(operands[0]);
                        int senateControl = RegisterFile.getValue(operands[1]);

                        RegisterFile.updateRegister(operands[0], senateControl);
                        RegisterFile.updateRegister(operands[1], houseControl);

                        SystemIO.printString("Parties are switched for chambers of Congress!\n");
                    }
                }));

        instructionList.add(
                new BasicInstruction("ref $t0, -100",
                "Referendum for secession: hold a referendum for n states' independence. Flip a coin each time to see if the number of states in ($t0) decreases",
                BasicInstructionFormat.I_FORMAT,
                "000110 fffff 00010 ssssssssssssssss",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        int referendums = operands[1] << 16 >> 16;
                        int statesLeaving = 0;

                        Random random = new Random();

                        if (referendums > 0) {
                            for (int i = 0; i < referendums; i++) {
                                int numberOfStates = RegisterFile.getValue(operands[0]);
                                int coinFlip = random.nextInt(2); // 0 = Stay, 1 = Leave

                                if (coinFlip == 1) {
                                    RegisterFile.updateRegister(operands[0], numberOfStates - 1);
                                    statesLeaving++;
                                }
                            }
                            SystemIO.printString("" + (statesLeaving) + " states voted to leave the Union!\n");
                        } else {
                            SystemIO.printString("Number of referendums has to be greater than 0!\n");
                            return;
                        }
                    }
                }));

        instructionList.add(
                new BasicInstruction("query $t0, $t1",
                "Election query: flip a coin to determine if recent election results were legitimate. If not, restore the previous values of ($t0) and ($t1)",
                BasicInstructionFormat.R_FORMAT,
                "000111 fffff sssss 00000 00000 000101",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();

                        Random random = new Random();
                        int wasLegit = random.nextInt(4); // 0-2 = Legit, 3 = Not legit

                        if (wasLegit == 3) {
                            int oldTeamOneEV = RegisterFile.getValue(17);
                            int oldTeamTwoEV = RegisterFile.getValue(18);

                            RegisterFile.updateRegister(operands[0], oldTeamOneEV);
                            RegisterFile.updateRegister(operands[1], oldTeamTwoEV);
                            SystemIO.printString("Election results reverted to " + oldTeamOneEV + "-" + oldTeamTwoEV + "!\n");
                        } else {
                            SystemIO.printString("No irregularities in election results!\n");
                        }
                    }
                }));

        instructionList.add(
                new BasicInstruction("revert $t0",
                "Revert policies: restore the previous stability of the nation in ($t0)",
                BasicInstructionFormat.R_FORMAT,
                "000111 fffff 00000 00000 00000 000110",
                new SimulationCode()
                {
                    public void simulate(ProgramStatement statement) throws ProcessingException
                    {
                        int[] operands = statement.getOperands();
                        int oldStability = RegisterFile.getValue(19);

                        RegisterFile.updateRegister(operands[0], oldStability);
                        SystemIO.printString("Reverted to old stability of " + oldStability + "\n");
                    }
                }));

        // Only adding the jump again because I didn't know that all other MIPS functions would be unusable and I need it for one of my examples
        instructionList.add(
                new BasicInstruction("j target",
                        "Jump unconditionally : Jump to statement at target address",
                        BasicInstructionFormat.J_FORMAT,
                        "000010 ffffffffffffffffffffffffff",
                        new SimulationCode()
                        {
                            public void simulate(ProgramStatement statement) throws ProcessingException
                            {
                                int[] operands = statement.getOperands();
                                Globals.instructionSet.processJump(
                                        ((RegisterFile.getProgramCounter() & 0xF0000000)
                                                | (operands[0] << 2)));
                            }
                        }));

    }
}