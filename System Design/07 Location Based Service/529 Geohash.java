/**

Geohash is a hash function that convert a location coordinate pair into a base32 string.

Check how to generate geohash on wiki: Geohash or just google it for more details.

Try http://geohash.co/.

You task is converting a (latitude, longitude) pair into a geohash string.

Have you met this question in a real interview? Yes
Example
Given lat = 39.92816697, lng = 116.38954991 and precision = 12 which indicate how long the hash string should be. You should return wx4g0s8q3jf9.

The precision is between 1 ~ 12.
 
**/

public class GeoHash {
    /**
     * @param latitude, longitude a location coordinate pair 
     * @param precision an integer between 1 to 12
     * @return a base32 string
     */

/*
    public String encode(double latitude, double longitude, int precision) {
        // Write your code here
        String charSet = "0123456789bcdefghjkmnpqrstuvwxyz";
        StringBuilder log = new StringBuilder();
        StringBuilder lat = new StringBuilder();
        
        double start = -90, end = 90;
        for (int i = 0; i < 6; ++i) {
            double mid = start + (end - start) / 2;
            
            if (longitude < mid) {
                lat.append("0");
                end = mid;
            }
            else {
                lat.append("1");
                start = mid;
            }
        }
        
        start = -180;
        end = 180;
        
        for (int i = 0; i < 6; ++i) {
            double mid = start + (end - start) / 2;
            
            if (longitude < mid) {
                log.append("0");
                end = mid;
            }
            else {
                log.append("1");
                start = mid;
            }
        }
        
        StringBuilder geoCode = new StringBuilder();
        
        for (int i = 0; i < 6; ++i) {
            geoCode.append(log.charAt(i)).append(lat.charAt(i));
        }
        
        long geoHash = Long.parseLong(geoCode.toString());
        
        StringBuilder res = new StringBuilder();
        
        while (geoHash > 0) {
            res.insert(0, charSet.charAt((int)(geoHash % 32)));
            geoHash >>= 5;
        }
        
        return res.toString();
        
    }
    
*/

    public String encode(double latitude, double longitude, int precision) {
        // Write your code here
        String _base32 = "0123456789bcdefghjkmnpqrstuvwxyz";
        String lat_bin = getBin(latitude, -90, 90);
        String lng_bin = getBin(longitude, -180, 180);
        
        StringBuffer hash_code = new StringBuffer();
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < 30; ++i) {
            b.append(lng_bin.charAt(i));
            b.append(lat_bin.charAt(i));
        }

        for (int i = 0; i < 60; i += 5) {
            int index = b2i(b.substring(i, i + 5));
            hash_code.append(_base32.charAt(index));
        }
        return hash_code.substring(0, precision);
    }

    public int b2i(String bin) {
        return Integer.parseInt(bin, 2);
    }

    public String getBin(double value, double left, double right) {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < 30; ++i) {
            double mid = (left + right) / 2.0;
            if (value > mid) {
                left = mid;
                b.append("1");
            } else {
                right = mid;
                b.append("0");
            }
        }
        return b.toString();
    }
}

/**

		
**/