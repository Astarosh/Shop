import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//������� �������� ����� Shop
public class Shop{
	//������ �������� ��������
	private static String name = "������� ������� ���������� �������";
	//������������� ���� � ������
    private static final String FILENAME1 = "\\src\\SportEquipment.xml";
    private static final String FILENAME2 = "\\src\\RentEquipment.xml";
    public static void main(String[] args) throws IOException{
    	//��������� ������ �� XML ������
    	//��������� ������ � ������� �������
    	SportEquipment readAvailableInventory = new SportEquipment();
    	readAvailableInventory.readAvailable(Shop.FILENAME1);
    	//��������� ������ � ������ �� ������ �������
    	RentUnit readRentInventory = new RentUnit();
    	readRentInventory.readRented(Shop.FILENAME2);
    	//�������� �������� � ��������
    	BufferedReader a = new BufferedReader(new InputStreamReader(System.in));
    	Out:
    	while(true){
        	System.out.println("��� ������������ " + name + "!");
        	System.out.println("������� ����� ���-������ ��������? �������� �� ��� ���");
    		String b = a.readLine();
    	    if(b.equals("��")){
        	    while(true){
        	        System.out.println("�������� ��� ������� �����.");
        		    System.out.print("��������� ���������: ");
        		    for(Object key : readAvailableInventory.availableNames){
        		        System.out.print(" ");
        		        System.out.print(key);
        		    }
        		    System.out.println();
        		    String c = a.readLine();
        		    if(c != "" || c != " "){
        			    try {
        		            int f = Arrays.asList(readAvailableInventory.availableNames).indexOf(c);
        		            if(f != -1){
            		            int g = Integer.parseInt(readAvailableInventory.getGoods(c));
                    	        System.out.print("��������� ����������: ");
                    	        System.out.println(g);
                    	        System.out.print("���� �� 1 ����: ");
                    	        System.out.println(readAvailableInventory.prices[f] + "$");
                    	    } else{
                    		    System.out.println("�� ����������� ����� ��������!");
                    		    continue;
                    	    }
                	    } catch(Exception e){
                		    System.out.println("�� ����������� ����� ��������!");
                		    continue;
                	    }
        		    } else{
        		        continue;
        		    }
        		    System.out.println("������� ������� �����(����. 3 �� ������ �� 1 ��������)?");
        		    while(true){
        		    	int g = Integer.parseInt(readAvailableInventory.getGoods(c));
        		        try{
        		            int d = Integer.parseInt(a.readLine());
        		            if(d < 4 && d > 0 && d <= g){
        		                System.out.println("������� ���� �������");
        		                String h = a.readLine();
        		                if(!h.equals("") || !h.equals(" ") || h.length() > 2){
        		    	            int rentedQ = 0;
        	    		            for(Object key : readRentInventory.rentedNames){
        	    		                if(key.equals(h)){
        	    		    	            rentedQ += 1;
        	    		                }
        	    		            }
        	    		            if(rentedQ != 3 && d + rentedQ < 4){
        	    		                System.out.println("��� ����� ������");
        	    		                System.out.println("������� ����������?");
        	    		                String rt = a.readLine();
            	    		            if(rt.equals("��")){
            	    		            	break;
            	    		            } else if(rt.equals("���")){
            	    		            	System.out.println("����� ����������! �������������!");
            	    			            break Out;
            	    		            } else{
            	    		            	System.out.println("������� �� ��� ���");
            	    		            	continue;
            	    		            }
        	    		            } else{
        	    		            	System.out.println("�� ��� ����� " + rentedQ + " ������(�������� ����� 3).");
        	    		            	break Out;
        	    		            }
        	    	            }
        		                } else{
        		                	if(g > 3){
        		                		System.out.println("������� ���������� �� 1 �� 3");
        		                	} else{
        		                		System.out.println("������� ���������� �� 1 �� " + g);
        		                	}
        		    	            continue;
        		                }
        	            } catch(Exception e){
        		            System.out.println("������� ���������� �� 1 �� 3");
        		            continue;
        	            }
        		    }
        		}
        	} else if(b.equals("���")){
        		System.out.println("������� ���������� ������� �� �������?");
        		while(true){
        		    String io = a.readLine();
        		    if(io.equals("��")){
        			    System.out.print("��������� ���������: ");
            		    for(Object key : readAvailableInventory.availableNames){
            		        System.out.print(" ");
            		        System.out.print(key);
            		    }
            		    System.out.println();
            		    System.out.println("������� ����������?");
            		    String uy = a.readLine();
            		    if(uy.equals("��")){
            		    	break;
            		    } else if(uy.equals("���")){
            		    	System.out.println("����� ����������!");
            		    	break Out;
            		    } else{
            		    	System.out.println("������� �� ��� ���");
					    	continue;
            		    }
        		    } else if(io.equals("���")){
        			    System.out.println("������� ���������� �� ��������� � �������?");
        			    String ui = a.readLine();
        			    if(ui.equals("��")){
        				    for(int i = 0; i < readRentInventory.getUnits().length; i++){
        					    System.out.println(readRentInventory.getUnits()[i] + " " + readRentInventory.rentedQuantity[i]);
        				    }
    					    System.out.println("������� ����������?");
    					    String er = a.readLine();
    					    if (er.equals("��")){
    					    	break;
    					    } else if(er.equals("���")){
    					    	break Out;
    					    } else {
    					    	System.out.println("������� �� ��� ���");
    					    	continue;
    					    }
        			    } else {
        				    break Out;
        			    }
        		    } else{
        			    System.out.println("������� �� ��� ���");
        			    continue;
        		    }
        	    }
        	} else{
        		System.out.println("�������� �� ��� ���.");
        		continue;
        	}
    	}
    	}
    }