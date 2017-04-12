import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//Ñîçäàåì îñíîâíîé êëàññ Shop
public class Shop{
	//Ââîäèì íàçâàíèå ìàãàçèíà
	private static String name = "Ìàãàçèí ïðîêàòà ñïîðòèâíûõ òîâàðîâ";
	//Óñòàíàâëèâàåì ïóòè ê ôàéëàì
    private static final String FILENAME1 = "\\SportEquipment.xml";
    private static final String FILENAME2 = "\\RentEquipment.xml";
    public static void main(String[] args) throws IOException{
    	//Çàãðóæàåì äàííûå èç XML ôàéëîâ
    	//Çàãðóæàåì äàííûå î òåêóùèõ òîâàðàõ
    	SportEquipment readAvailableInventory = new SportEquipment();
    	readAvailableInventory.readAvailable(Shop.FILENAME1);
    	//Çàãðóæàåì äàííûå î âçÿòûõ íà ïðîêàò òîâàðàõ
    	RentUnit readRentInventory = new RentUnit();
    	readRentInventory.readRented(Shop.FILENAME2);
    	//Íà÷èíàåì ðàçãîâîð ñ êëèåíòîì
    	BufferedReader a = new BufferedReader(new InputStreamReader(System.in));
    	Out:
    	while(true){
        	System.out.println("Âàñ ïðèâåòñòâóåò " + name + "!");
        	System.out.println("Æåëàåòå âçÿòü ÷òî-íèáóäü íàïðîêàò? Íàïèøèòå äà èëè íåò");
    		String b = a.readLine();
    	    if(b.equals("äà")){
        	    while(true){
        	        System.out.println("Íàïèøèòå ÷òî æåëàåòå âçÿòü.");
        		    System.out.print("Äîñòóïíûé èíâåíòàðü: ");
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
                    	        System.out.print("Äîñòóïíîå êîëè÷åñòâî: ");
                    	        System.out.println(g);
                    	        System.out.print("Öåíà çà 1 äåíü: ");
                    	        System.out.println(readAvailableInventory.prices[f] + "$");
                    	    } else{
                    		    System.out.println("Âû íåïðàâèëüíî ââåëè íàçâàíèå!");
                    		    continue;
                    	    }
                	    } catch(Exception e){
                		    System.out.println("Âû íåïðàâèëüíî ââåëè íàçâàíèå!");
                		    continue;
                	    }
        		    } else{
        		        continue;
        		    }
        		    System.out.println("Ñêîëüêî æåëàåòå âçÿòü(ìàêñ. 3 åä òîâàðà íà 1 ÷åëîâåêà)?");
        		    while(true){
        		    	int g = Integer.parseInt(readAvailableInventory.getGoods(c));
        		        try{
        		            int d = Integer.parseInt(a.readLine());
        		            if(d < 4 && d > 0 && d <= g){
        		                System.out.println("Ââåäèòå âàøó ôàìèëèþ");
        		                String h = a.readLine();
        		                if(!h.equals("") || !h.equals(" ") || h.length() > 2){
        		    	            int rentedQ = 0;
        	    		            for(Object key : readRentInventory.rentedNames){
        	    		                if(key.equals(h)){
        	    		    	            rentedQ += 1;
        	    		                }
        	    		            }
        	    		            if(rentedQ != 3 && d + rentedQ < 4){
        	    		                System.out.println("Âàø çàêàç ïðèíÿò");
        	    		                System.out.println("Æåëàåòå ïðîäîëæèòü?");
        	    		                String rt = a.readLine();
            	    		            if(rt.equals("äà")){
            	    		            	break;
            	    		            } else if(rt.equals("íåò")){
            	    		            	System.out.println("Âñåãî íàèëó÷øåãî! Âîçâðàùàéòåñü!");
            	    			            break Out;
            	    		            } else{
            	    		            	System.out.println("Ââåäèòå äà èëè íåò");
            	    		            	continue;
            	    		            }
        	    		            } else{
        	    		            	System.out.println("Âû óæå âçÿëè " + rentedQ + " òîâàðà(äîñòóïíî âçÿòü 3).");
        	    		            	break Out;
        	    		            }
        	    	            }
        		                } else{
        		                	if(g > 3){
        		                		System.out.println("Ââåäèòå êîëè÷åñòâî îò 1 äî 3");
        		                	} else{
        		                		System.out.println("Ââåäèòå êîëè÷åñòâî îò 1 äî " + g);
        		                	}
        		    	            continue;
        		                }
        	            } catch(Exception e){
        		            System.out.println("Ââåäèòå êîëè÷åñòâî îò 1 äî 3");
        		            continue;
        	            }
        		    }
        		}
        	} else if(b.equals("íåò")){
        		System.out.println("Æåëàåòå ïîñìîòðåòü îñòàòêè ïî òîâàðàì?");
        		while(true){
        		    String io = a.readLine();
        		    if(io.equals("äà")){
        			    System.out.print("Äîñòóïíûé èíâåíòàðü: ");
            		    for(Object key : readAvailableInventory.availableNames){
            		        System.out.print(" ");
            		        System.out.print(key);
            		    }
            		    System.out.println();
            		    System.out.println("Æåëàåòå ïðîäîëæèòü?");
            		    String uy = a.readLine();
            		    if(uy.equals("äà")){
            		    	break;
            		    } else if(uy.equals("íåò")){
            		    	System.out.println("Âñåãî íàèëó÷øåãî!");
            		    	break Out;
            		    } else{
            		    	System.out.println("Ââåäèòå äà èëè íåò");
					    	continue;
            		    }
        		    } else if(io.equals("íåò")){
        			    System.out.println("Æåëàåòå ïîñìîòðåòü íà èíâåíòàðü â ïðîêàòå?");
        			    String ui = a.readLine();
        			    if(ui.equals("äà")){
        				    for(int i = 0; i < readRentInventory.getUnits().length; i++){
        					    System.out.println(readRentInventory.getUnits()[i] + " " + readRentInventory.rentedQuantity[i]);
        				    }
    					    System.out.println("Æåëàåòå ïðîäîëæèòü?");
    					    String er = a.readLine();
    					    if (er.equals("äà")){
    					    	break;
    					    } else if(er.equals("íåò")){
    					    	break Out;
    					    } else {
    					    	System.out.println("Ââåäèòå äà èëè íåò");
    					    	continue;
    					    }
        			    } else {
        				    break Out;
        			    }
        		    } else{
        			    System.out.println("Ââåäèòå äà èëè íåò");
        			    continue;
        		    }
        	    }
        	} else{
        		System.out.println("Íàïèøèòå äà èëè íåò.");
        		continue;
        	}
    	}
    	}
    }
