package in.nit.hc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface CollectionUtil {
	
	public static Map<Long,String> convertToMap(List<Object[]> list) {
		/*
		 * Map<Long,String> map2 = new HashMap<>(); 
		 * 	for(Object[] obArr : list) {
		 * 		map2.put( 
		 * 				Long.valueOf(obArr[0].toString()),
		 * 				obArr[1].toString() 
		 * 				); 
		 * }
		 */
		Map<Long,String> map = list
								.stream()
								.collect(Collectors.toMap(
										obArr -> Long.valueOf(obArr[0].toString()), //toMap(Function<>,Function<>) toMap() takes Function interface as input 2 times and return map  
										obArr -> obArr[1].toString())
										);
		
		return map;
	}
	
	public static Map<Long,String> convertToMapIndex(List<Object[]> list){
/*
		HashMap<Long, String> map = new HashMap<>();
		for(Object [] obArr : list) {
			map.put(
					Long.valueOf(obArr[0].toString()), 
					obArr[1].toString()+" "+obArr[2].toString()
					);
			
 		}
*/
		Map<Long,String> map = list
								.stream()
								.collect(Collectors.toMap(
										obAr -> Long.valueOf(obAr[0].toString()),  
										obAr -> obAr[1].toString()+" "+ obAr[2].toString())
											);
		
		return map;
	}
}
