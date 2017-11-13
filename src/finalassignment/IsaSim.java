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
	static int reg[] = new int[4];

	// Here the first program hard coded as an array
	static int progr[] = {
			// As minimal RISC-V assembler example
			0x00200093, // addi x1 x0 2
			0x00300113, // addi x2 x0 3
			0x002081b3, // add x3 x1 x2
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
                            switch (funct3){
                                case 0x0:
                                    reg[rd] = reg[rs1] + imm;
                                    break;
                                case 0x1:
                                    reg[rd] = reg[rs1] << imm;
                                    break;
                                case 0x4:
                                    break;
                                case 0x5:
                                    break;
                                case 0x6:
                                    break;
                                case 0x7:
                                    break;
                            }
                            break;
                        case 0x33:
                                break;
                        case 0x03:
                                break;
                        case 0x67:
                                break;
                        case 0x23:
                                break;
                        case 0x37:
                                break;
                        case 0x6F:
                                break;
			default:
				System.out.println("Opcode " + opcode + " not yet implemented");
				break;
			}

			++pc; // We count in 4 byte words
			if (pc >= progr.length) {
				break;
			}
			for (int i = 0; i < reg.length; ++i) {
				System.out.print(reg[i] + " ");
			}
			System.out.println();
		}

		System.out.println("Program exit");

	}

}
