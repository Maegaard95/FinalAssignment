package finalassignment;

/**
 * RISC-V Instruction Set Simulator
 *
 * A tiny first step to get the simulator started. Can execute just a single
 * RISC-V instruction.
 *
 * @author Martin Schoeberl (martin@jopdesign.com)
 *
 */
public class IsaSim {

    static int pc;
    static int reg[] = new int[32];
    static int memory[] = new int[1000000];

    // Here the first program hard coded as an array
    static int progr[] = {
        // As minimal RISC-V assembler example
        0x00200093, // addi x1 x0 2
        0x00300113, // addi x2 x0 3
        0x002081b3, // add x3 x1 x2
        0x402181b3,
        0x00f18193,
        0x0001f193,
        0x001141b3,
        
    };

    public static void main(String[] args) {

        System.out.println("Hello RISC-V World!");

        pc = 0;

        for (;;) {

            int instr = progr[pc];
            int opcode = instr & 0x7f;
            int rd = (instr >> 7) & 0x01f;
            int rs1 = (instr >> 15) & 0x01f;
            int imm = (instr >> 20);
            int funct3 = (instr >> 12) & 0x7;
            int funct7 = (instr >> 25);
            int imm2 = (funct7 << 5) | rd;
            int rs2 = (instr >> 20) & 0x01f;

            switch (opcode) {

                case 0x13:
                    switch (funct3) {
                        case 0x0: //addi start
                            reg[rd] = reg[rs1] + imm;
                            break; //addi slut
                        case 0x1: //slli start
                            reg[rd] = reg[rs1] << imm;
                            break;//slli slut
                        case 0x4://xori start
                            reg[rd] = reg[rs1] ^ imm;
                            break; //xori slut
                        case 0x5:
                            switch (funct7) {
                                case 0x0://srli start
                                    reg[rd] = reg[rs1] >> imm;
                                    break;//srli slut
                                case 0x10://srai
                                    reg[rd] = reg[rs1] >>> imm;
                                    break;//srai slut
                                default:
                                    System.out.println("Opcode " + opcode + " not yet implemented");
                                    break;
                            }
                            break;
                        case 0x6: //ori start
                            reg[rd] = reg[rs1] | imm;
                            break; //ori slut
                        case 0x7: //andi start
                            reg[rd] = reg[rs1] & imm;
                            break; //andi slut
                        default:
                            System.out.println("Opcode " + opcode + " not yet implemented");
                            break;
                    }
                    break;
                case 0x33:
                    switch (funct3) {
                        case 0x0:
                            switch (funct7) {
                                case 0x0: //add start
                                    reg[rd] = reg[rs1] + reg[rs2];
                                    break; //add slut
                                case 0x20: //sub start
                                    reg[rd] = reg[rs1] - reg[rs2];
                                    break; //sub slut
                                default:
                                    System.out.println("Opcode " + opcode + " not yet implemented");
                                    break;
                            }
                            break;
                        case 0x1: //sll start
                            reg[rd] = reg[rs1] << reg[rs2];
                            break; //sll slut
                        case 0x3:
                            switch (funct7) {
                                case 0x8:
                                    break;
                                case 0xc:
                                    break;
                                default:
                                    System.out.println("Opcode " + opcode + " not yet implemented");
                                    break;
                            }
                            break;
                        case 0x4:
                            reg[rd] = reg[rs1] ^ reg[rs2];
                            break;
                        case 0x5:
                            switch (funct7) {
                                case 0x0:
                                    reg[rd] = reg[rs1] >> reg[rs2];
                                    break;
                                case 0x20:
                                    reg[rd] = reg[rs1] >>> reg[rs2];
                                    break;
                                default:
                                    System.out.println("Opcode " + opcode + " not yet implemented");
                                    break;
                            }
                        case 0x6:
                            reg[rd] = reg[rs1] | reg[rs2];
                            break;
                        case 0x7:
                            reg[rd] = reg[rs1] & reg[rs2];
                            break;
                    }
                    break;
                case 0x3:
                    switch (funct3) {
                        case 0x0:

                            break;
                        case 0x1:
                            break;
                        case 0x2:

                            break;
                        case 0x3:
                            break;
                        case 0x4:
                            break;
                        case 0x5:
                            break;
                        case 0x6:
                            break;
                        default:
                            System.out.println("Opcode " + opcode + " not yet implemented");
                            break;
                    }
                    break;
                case 0x67:
                    switch (funct3) {
                        case 0x0:
                            switch (funct7) {
                                case 0x0:
                                    break;
                                case 0x1:
                                    break;
                                default:
                                    System.out.println("Opcode " + opcode + " not yet implemented");
                                    break;
                            }
                            break;
                        case 0x1:
                            break;
                        case 0x4:
                            break;
                        case 0x5:
                            break;
                        case 0x6:
                            break;
                        case 0x7:
                            break;
                        default:
                            System.out.println("Opcode " + opcode + " not yet implemented");
                            break;
                    }
                    break;
                case 0x23:
                    switch (funct3) {
                        case 0x0:
                            break;
                        case 0x1:
                            break;
                        case 0x2:
                            break;
                        case 0x7:
                            break;
                        default:
                            System.out.println("Opcode " + opcode + " not yet implemented");
                            break;
                    }
                    break;
                case 0x37:
                    break;
                case 0x6F:
                    break;
                default:
                    System.out.println("Opcode " + opcode + " not yet implemented");
                    break;
            }

            for (int i = 0; i < reg.length; ++i) {
                System.out.print("Reg" + i + " " + reg[i] + " ");
            }
            System.out.println();
            ++pc;
            // We count in 4 byte words
            if (pc >= progr.length) {
                break;
            }
        }

        System.out.println("Program exit");

    }

}
