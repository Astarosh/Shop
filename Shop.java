import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//Создаем основной класс Shop
public class Shop{
	//Вводим название магазина
	private static String name = "Магазин проката спортивных товаров";
	//Устанавливаем пути к файлам
    private static final String FILENAME1 = "\\Shop-master\\SportEquipment.xml";
    private static final String FILENAME2 = "\\Shop-master\\RentEquipment.xml";
    public static void main(String[] args) throws IOException{
    	//Загружаем данные из XML файлов
    	//Загружаем данные о текущих товарах
    	SportEquipment readAvailableInventory = new SportEquipment();
    	readAvailableInventory.readAvailable(Shop.FILENAME1);
    	//Загружаем данные о взятых на прокат товарах
    	RentUnit readRentInventory = new RentUnit();
    	readRentInventory.readRented(Shop.FILENAME2);
    	//Начинаем разговор с клиентом
    	BufferedReader a = new BufferedReader(new InputStreamReader(System.in));
    	Out:
    	while(true){
        	System.out.println("Вас приветствует " + name + "!");
        	System.out.println("Желаете взять что-нибудь напрокат? Напишите да или нет");
    		String b = a.readLine();
    	    if(b.equals("да")){
        	    while(true){
        	        System.out.println("Напишите что желаете взять.");
        		    System.out.print("Доступный инвентарь: ");
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
                    	        System.out.print("Доступное количество: ");
                    	        System.out.println(g);
                    	        System.out.print("Цена за 1 день: ");
                    	        System.out.println(readAvailableInventory.prices[f] + "$");
                    	    } else{
                    		    System.out.println("Вы неправильно ввели название!");
                    		    continue;
                    	    }
                	    } catch(Exception e){
                		    System.out.println("Вы неправильно ввели название!");
                		    continue;
                	    }
        		    } else{
        		        continue;
        		    }
        		    System.out.println("Сколько желаете взять(макс. 3 ед товара на 1 человека)?");
        		    while(true){
        		    	int g = Integer.parseInt(readAvailableInventory.getGoods(c));
        		        try{
        		            int d = Integer.parseInt(a.readLine());
        		            if(d < 4 && d > 0 && d <= g){
        		                System.out.println("Введите вашу фамилию");
        		                String h = a.readLine();
        		                if(!h.equals("") || !h.equals(" ") || h.length() > 2){
        		    	            int rentedQ = 0;
        	    		            for(Object key : readRentInventory.rentedNames){
        	    		                if(key.equals(h)){
        	    		    	            rentedQ += 1;
        	    		                }
        	    		            }
        	    		            if(rentedQ != 3 && d + rentedQ < 4){
        	    		                System.out.println("Ваш заказ принят");
        	    		                System.out.println("Желаете продолжить?");
        	    		                String rt = a.readLine();
            	    		            if(rt.equals("да")){
            	    		            	break;
            	    		            } else if(rt.equals("нет")){
            	    		            	System.out.println("Всего наилучшего! Возвращайтесь!");
            	    			            break Out;
            	    		            } else{
            	    		            	System.out.println("Введите да или нет");
            	    		            	continue;
            	    		            }
        	    		            } else{
        	    		            	System.out.println("Вы уже взяли " + rentedQ + " товара(доступно взять 3).");
        	    		            	break Out;
        	    		            }
        	    	            }
        		                } else{
        		                	if(g > 3){
        		                		System.out.println("Введите количество от 1 до 3");
        		                	} else{
        		                		System.out.println("Введите количество от 1 до " + g);
        		                	}
        		    	            continue;
        		                }
        	            } catch(Exception e){
        		            System.out.println("Введите количество от 1 до 3");
        		            continue;
        	            }
        		    }
        		}
        	} else if(b.equals("нет")){
        		System.out.println("Желаете посмотреть остатки по товарам?");
        		while(true){
        		    String io = a.readLine();
        		    if(io.equals("да")){
        			    System.out.print("Доступный инвентарь: ");
            		    for(Object key : readAvailableInventory.availableNames){
            		        System.out.print(" ");
            		        System.out.print(key);
            		    }
            		    System.out.println();
            		    System.out.println("Желаете продолжить?");
            		    String uy = a.readLine();
            		    if(uy.equals("да")){
            		    	break;
            		    } else if(uy.equals("нет")){
            		    	System.out.println("Всего наилучшего!");
            		    	break Out;
            		    } else{
            		    	System.out.println("Введите да или нет");
					    	continue;
            		    }
        		    } else if(io.equals("нет")){
        			    System.out.println("Желаете посмотреть на инвентарь в прокате?");
        			    String ui = a.readLine();
        			    if(ui.equals("да")){
        				    for(int i = 0; i < readRentInventory.getUnits().length; i++){
        					    System.out.println(readRentInventory.getUnits()[i] + " " + readRentInventory.rentedQuantity[i]);
        				    }
    					    System.out.println("Желаете продолжить?");
    					    String er = a.readLine();
    					    if (er.equals("да")){
    					    	break;
    					    } else if(er.equals("нет")){
    					    	break Out;
    					    } else {
    					    	System.out.println("Введите да или нет");
    					    	continue;
    					    }
        			    } else {
        				    break Out;
        			    }
        		    } else{
        			    System.out.println("Введите да или нет");
        			    continue;
        		    }
        	    }
        	} else{
        		System.out.println("Напишите да или нет.");
        		continue;
        	}
    	}
    	}
    }
