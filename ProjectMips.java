import java.io.File;
import java.util.*;

public class ProjectMips {
	
	
	private static LinkedList<Integer> memory = new LinkedList<Integer>();

	public static long[] registers = {0L,0L,0L,0L,0L,0L,0L,0L,
        0L,0L,0L,0L,0L,0L,0L,0L,
        0L,0L,0L,0L,0L,0L,0L,0L,
        0L,0L,0L,0L,0L,0L,0L,0L,};
	

	public static void main(String[] args) {
	
		registers[4] = 1L;
		registers[5] = 2147479908L;
		registers[6] = 2147479916L;
		registers[28] = 268468224L;
		registers[29] = 2147479904L;
		
		 try{
	         // for each string in string array 
			 System.out.println("Input name of the file:");
             Scanner input = new Scanner(System.in);
             String f = input.next();
             File file = new File(f);
             Scanner sc = new Scanner(file);
	         int mode = 0;
	         while(sc.hasNext()){
	             memory.add(sc.nextInt(16));
	         }
	         Iterator<Integer> itr = memory.iterator();
	         if(itr.hasNext())
	        	 mode = itr.next();
	         while(itr.hasNext()){
	        	 Decode(itr.next());
	        	 if((mode == 1)&&(itr.hasNext()))
	        		 PrintRegister();
	         }
	         PrintRegister();
	      }catch(Exception excep){
	       
	         excep.printStackTrace();
	      }
          
		

	}
	
	private static void Decode(Integer tok){
		StringBuffer inst = new  StringBuffer(Integer.toBinaryString(tok));
		
		// because java will not display entire bit sequence
		while(inst.length()<32){
			inst.insert(0, 0);
		}
		String opcode = inst.substring(0, 6);
		int rt, rd, rs, shamt, immdt;
		String funct,imm;
		
		switch(opcode){
		case"000000": 
			rt = Integer.parseInt(inst.substring(11, 16), 2);
		    rd = Integer.parseInt(inst.substring(16, 21), 2);
		    rs = Integer.parseInt(inst.substring(6, 11), 2);
		    shamt = Integer.parseInt(inst.substring(21,26), 2);
			funct = inst.substring(26, 32);
			
			if(funct.equals("000000")){ // shift left logical
				registers[rd] = (int)registers[rt] << shamt;
			} if(funct.equals("100000")){
				registers[rd] = (int)registers[rs] + (int)registers[rt];
			} if(funct.equals("100001")){// add (with an exception to overflow)
				registers[rd] = registers[rs] + registers[rt];
				registers[rd] = (int)registers[rd];
			} if(funct.equals("000010")){// shift right logical
				registers[rd] = (int)registers[rt] >> shamt;
			} if(funct.equals("100100")){// Logical AND operation
				registers[rd] = (int)registers[rs] & (int)registers[rt];
			} if(funct.equals("100111")){// Logical NOR operation
				registers[rd] =  ~ ((int)registers[rt]|(int)registers[rs]);
			} if(funct.equals("101010")){// Set to one if less than
			       if((int)registers[rs]<(int)registers[rt]) {
			    	   registers[rd] = 1;
			       } else {
			    	   registers[rd] = 0;
			       }
			} if(funct.equals("101011")){// Set to one if less then (exception to overflow)
				if(registers[rs]<registers[rt]) {
					registers[rd] = 1;
				} else {
					registers[rd] = 0;
				}
			} if(funct.equals("001100")){// call to the kernel
				System.out.println("Program ended by syscall");
				return;
			}
			break;
		
		
		case"001001": //add intermediate (consider exception to overflow)
			rt = Integer.parseInt(inst.substring(11, 16), 2);
			rs = Integer.parseInt(inst.substring(6, 11), 2);
			imm = inst.substring(16, 32);
			immdt = Integer.parseInt(imm, 2);
			registers[rt] = registers[rs] + immdt;
			registers[rt] = (int)registers[rt];
			break;
			
			
		case"001010": // set less than (consider immediate)
			rt = Integer.parseInt(inst.substring(11, 16), 2);
			rs = Integer.parseInt(inst.substring(6, 11), 2);
			imm = inst.substring(16, 32);
			if(imm.charAt(0)==1)//sign extension for immediate 
				imm = "1111111111111111" + imm;		
			immdt = Integer.parseInt(imm, 2);
			  if(((int)registers[rs]<immdt)){
				  registers[rt] = 1;
   		       } else {
   		    	   registers[rt] = 0;
   		       }
			break;
			
			
		case"001111": // load upper intermediate
			rt = Integer.parseInt(inst.substring(11, 16), 2);
			immdt = Integer.parseInt(inst.substring(16, 32), 2);
			registers[rt] = immdt << 16;
			break;
		}
		
	}
	
	private static void PrintRegister(){
		String[] register = new String[32];
		for(int i = 0;i<32;i++){
			register[i] = Integer.toHexString((int)registers[i]);
			while(register[i].length()<8){
				register[i] = "0" + register[i];
			}
			register[i] = "0x" + register[i];
		}

		System.out.println("$0: "+register[0]+" "+"$1: "+register[1]+" "+"$2: "+register[2]+" "+"$3: "+register[3]);
		System.out.println("$4: "+register[4]+" "+"$5: "+register[5]+" "+"$6: "+register[6]+" "+"$7: "+register[7]);
		System.out.println("$8: "+register[8]+" "+"$9: "+register[9]+" "+"$10:"+register[10]+" "+"$11:"+register[11]);
		System.out.println("$12:"+register[12]+" "+"$13:"+register[13]+" "+"$14:"+register[14]+" "+"$15:"+register[15]);
		System.out.println("$16:"+register[16]+" "+"$17:"+register[17]+" "+"$18:"+register[18]+" "+"$19:"+register[19]);
		System.out.println("$20:"+register[20]+" "+"$21:"+register[21]+" "+"$22:"+register[22]+" "+"$23:"+register[23]);
		System.out.println("$24:"+register[24]+" "+"$25:"+register[25]+" "+"$26:"+register[26]+" "+"$27:"+register[27]);
		System.out.println("$28:"+register[28]+" "+"$29:"+register[29]+" "+"$30:"+register[30]+" "+"$31:"+register[31]);
		System.out.println("========================================");
		
	}

}
