/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.BitSet;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asadi
 */
public class JIBitArray 
{
    private Vector  _Bits = new Vector ();

		
    public JIBitArray()
    {}
    
    public JIBitArray(BitSet Buf)
    {
        this(Buf.length());
        this.SetAll(false);
        
        for (int i=0 ; i< Buf.length(); i++)
            this.Set(i, Buf.get(i));
    }
    
    public  BitSet ToBitSet()
    {
        BitSet Buffer = new BitSet(this.Count());
        for (int i=0 ; i< this.Count(); i++)
            Buffer.set(i, this.Get(i));
        return Buffer;
    }
    
    public JIBitArray(byte[] bits) throws Exception
    {
            String st;
            for(byte b : bits)
            {
                    //st = FixLength(Convert.ToString(b,2),8);
                    st=String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                    AddBits(st);
            }
    }

		public JIBitArray(int[] bits) throws Exception
		{
			String st;
			for(int i : bits)
			{
				//st = FixLength(Convert.ToString(i,2),32);
                                st=String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0');
				AddBits(st);
			}
		}

		public JIBitArray(long[] bits) throws Exception
		{
			String st;
			for(long i : bits)
			{
				//st = FixLength(Convert.ToString(i,2),64);
                                st=String.format("%64s", Long.toBinaryString(i)).replace(' ', '0');
				AddBits(st);
			}
		}

		public JIBitArray(short[] bits) throws Exception
		{
			String st;
			for(short i : bits)
			{
				//st = FixLength(Convert.ToString(i,2),16);
                                st=String.format("%16s", Integer.toBinaryString(i)).replace(' ', '0');
				AddBits(st);
			}
		}

		public JIBitArray(boolean[] bits)
		{
			for(boolean b : bits)
			{
				_Bits.add(b);
			}
		}

		public JIBitArray(int length)
		{
			AddBlock(length,false);
		}

		public JIBitArray(int length,boolean defaultValue)
		{
			AddBlock(length,defaultValue);			
		}

		private void AddBlock(int length,boolean Value)
		{
			for(int i=0; i<length; i++)
				_Bits.add(Value);
		}
		

		private String FixLength(String num,int length)
		{
                       StringBuffer  Num = new StringBuffer (num);
			while(Num.length() < length)
				Num = Num.insert(0, "0");//  Insert(0,"0");
			return Num.toString();
		}

		private void AddBits(String bits) throws Exception
		{
                    //for(char ch in bits)
                    //{
                    //    if(ch == '0')
                    //        _Bits.add(false);
                    //    else if(ch == '1')
                    //        _Bits.add(true);
                    //    else
                    //        throw(new ArgumentException("bits Contain none 0 1 character"));
                    //}

                        for (int i=bits.length()-1 ; i >=0 ; i--)
                        {
                            if (bits.charAt(i) == '0')
                                _Bits.add(false);
                            else if (bits.charAt(i) == '1')
                                _Bits.add(true);
                            else
                                throw new Exception("bits Contain none 0 1 character");
                        }
		}

		/// <summary>
		/// Convert current System.Collections.JIBitArray to binary String
		/// </summary>
		/// <returns></returns>
		
        public  String ToStringBase()
        {
                String rt ="";// String. Empty;
                //foreach(boolean b in _Bits)
                for (int i = _Bits.size() - 1; i >= 0; i-- )
                {
                    if (((boolean)_Bits.get(i)) == false)
                        rt += '0';
                    else
                        rt += '1';
                }
                return rt;
        }
       
        public  String ToString()
        {
            //String rt = String.Empty;
            //foreach(boolean b in _Bits)
            BigInteger val=BigInteger.ZERO;

            for (int i = _Bits.size() - 1; i >= 0; i--)
            {
                if (((boolean)_Bits.get(i))==true)
                    val=val.add(BigInteger.ONE) ; //+=;
                if (i != 0)
                    val = val.shiftLeft(1);// << 1;
            }
            return val.toString();// ToString();
        }
        
        
        public String ToBase64String()
        {
        try {
            //byte []  Buffer=Base64.getDecoder().decode(this.jTextField1.getText());
            
            return Base64.getEncoder().encodeToString(this.GetBytes());
        } catch (Exception ex) {
            Logger.getLogger(JIBitArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  null;
        }

        /// <summary>
        /// Insert a bit at the spesific position in System.Collections.JIBitArray
        /// </summary>
        /// <param name="index">The zero-based index of the bit to insert</param>
        /// <param name="Value">The Boolean value to assign to the bit</param>
        public void Insert(int index,boolean Value)
        {
                _Bits.insertElementAt(Value, index); //Insert(index,Value);
        }

        /// <summary>
        /// Add a bit at the final position in System.Collections.JIBitArray
        /// </summary>
        /// <param name="Value">The Boolean value to assign to the bit</param>
        public void Add(boolean Value)
        {
                _Bits.add(Value);
        }

        /// <summary>
        /// Set the bit at the spesific position in System.Collection.JIBitArray
        /// </summary>
        /// <param name="index">The zero-based index of the bit to set</param>
        /// <param name="Value">The Boolean value to assign to the bit</param>
        public void Set(int index,boolean Value)
        {
                _Bits.set(index, Value); //[index] = Value;			
        }

        public boolean Set(int index, boolean[] values)
        {
            if( values.length+ index > _Bits.size())
                return false;

            for (int i = 0; i < values.length; i++)
                    _Bits.set(i + index, values[i]) ;//[i + index] = values[i];
            return true;
        }


        public boolean Set(int index,int length, long value)
        {
            if (length + index > _Bits.size())
                return false;

            for (int i = 0; i < length; i++ )
            {
                if ( value%2 == 0)
                {
                    _Bits.set(i + index, false);//[i + index] = false;
                }else
                {
                    _Bits.set(i + index, true);//_Bits[i + index] = true;
                }
                value = value >> 1;
            }
            
            return true;
        }


        public boolean Set(int index,int length, BigInteger value)
        {
            if (length + index > _Bits.size())
                return false;

            for (int i = 0; i < length; i++ )
            {
                _Bits.set(i + index, value.testBit(i));
                
            }
            
            return true;
        }

        public JIBitArray Append(byte[] bits)
        {
            String st;
            
            try 
            {
                for (byte b : bits)
                {
                    //st = FixLength(Convert.ToString(b, 2), 8);
                    st=FixLength(String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0') ,8);
                    AddBits(st);
                }
            } catch (Exception ex) 
            {
                    Logger.getLogger(JIBitArray.class.getName()).log(Level.SEVERE, null, ex);
            }
            return this;
        }

        public boolean Append(byte[] bits, int Size)
        {
            String st;
            int ctr = 0;
            try 
            {
                for (byte b : bits)
                {
                    //st = FixLength(Convert.ToString(b, 2), 8);
                    st=FixLength(String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0') ,8);
                    AddBits(st);
                    ctr++;
                    if (ctr >= Size)
                        break;
                }
            } catch (Exception ex) 
            {
                    Logger.getLogger(JIBitArray.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        public boolean Append(JIBitArray bits)
        {
            String st;
            int ctr = 0;
            try 
            {
                for (int i =0; i<bits.Count();i++)
                {
                    
                    
                     _Bits.add(bits.Get(i));
                    
                }
            } catch (Exception ex) 
            {
                    Logger.getLogger(JIBitArray.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }

        public boolean Set(int index, int length, int value)
        {
            if (length + index > _Bits.size())
                return false;

            for (int i = 0; i < length; i++)
            {
                if (value % 2 == 0)
                {
                    _Bits.set(i + index, false);//_Bits[i + index] = false;
                }
                else
                {
                    _Bits.set(i + index, true);//_Bits[i + index] = true;
                }
                value = value >> 1;
            }

            return true;
        }
        public boolean Set(int index, int length, byte value)
        {
            if (length + index > _Bits.size())
                return false;
            int vv = (int)(value&0xFFL);
            for (int i = 0; i < length; i++)
            {
                if ( (byte)(value&0x01L ) ==0)
                {
                    _Bits.set(i + index, false);//_Bits[i + index] = false;
                }
                else
                {
                    _Bits.set(i + index, true);//_Bits[i + index] = true;
                }
                value = (byte) (value>> 1);
            }

            return true;
        }

        public boolean Set(int index, int length,JIBitArray buffer)
        {
            if (length + index > _Bits.size())
                return false;

            for (int i = 0; i < length; i++)
            {
                _Bits.set(i + index, buffer.Get(i));////_Bits[i + index] = buffer.Get(i);
            }

            return true;
        }
        
        public void SetBuffer(int start , int size ,  JIBitArray Buffer)
        {

            for (int  i=0 ; i< size; i++)
            {
                _Bits.set(i + start,Buffer.Get(i));
            }
        }

		/// <summary>
		/// Get the value of a bit at the specific position in the System.Collections.JIBitArray
		/// </summary>
		/// <param name="index">The zero-based index of the value to get</param>
		/// <returns></returns>
		public boolean Get(int index)
		{
			return (boolean)_Bits.get(index);//[index];
		}

		/// <summary>
		/// Get the number of element actually contained in System.Collections.JIBitArray
		/// </summary>
		public int Count()
		{	
                    return _Bits.size(); 
		}

		/// <summary>
		/// Set all bits in System.Collections.JIBitArray to the specific value
		/// </summary>
		/// <param name="Value">The Boolean value to assign to all bits</param>
		public void SetAll(boolean Value)
		{
			for(int i=0; i<_Bits.size(); i++)
			{
				_Bits.set(i , Value);////_Bits[i] = Value;
			}
		}

		/// <summary>
		/// Inverts all the bits values in the current System.Collections.JIBitArray, so
		/// that elements set to true are changed to false, and elements set to false 
		/// are changed to true
		/// </summary>
		/// <returns></returns>
		public JIBitArray Not()
		{
			JIBitArray RArray = new JIBitArray(_Bits.size());
			for(int i=0; i<_Bits.size(); i++)
			{
				if((boolean)_Bits.get(i) == true)
					RArray.Set(i,false);
				else
					RArray.Set(i,true);
			}
			return RArray;
		}
		
		/// <summary>
		/// Retrives a SubJIBitArray from this instance. The SubJIBitArray start at the 
		/// specified bit position and has specified length
		/// </summary>
		/// <param name="index">The index of the start of SubJIBitArray</param>
		/// <param name="length">The number of bits in SubJIBitArray</param>
		/// <returns></returns>
		public JIBitArray SubJIBitArray(int index,int length)
		{
			JIBitArray RArray = new JIBitArray(length);
			int c=0;
			for(int i=index; i<index+length; i++)
				RArray.Set(c++,(boolean)_Bits.get(i));
			return RArray;
		}

		/// <summary>
		/// Performs the bitwise OR operation on the elements in the current System.Collections.JIBitArray 
		/// against the corresponding elements in the specified System.Collections.JIBitArray
		/// </summary>
		/// <param name="Value">The System.Collections.JIBitArray with which to perform the bitwise OR operation</param>
		/// <returns></returns>
		public JIBitArray Or(JIBitArray Value) throws Exception
		{
			JIBitArray RArray;
			int Max = _Bits.size() > Value._Bits.size() ? _Bits.size() : Value._Bits.size();
						
			RArray = new JIBitArray(Max);
			RArray._Bits = this._Bits;

			if(Max == RArray._Bits.size())
				FixLength(Value,Max);
			else
				FixLength(RArray,Max);
		
			for(int i=0; i<Max; i++)
				RArray.Set(i,(boolean)Value._Bits.get(i) | (boolean)RArray._Bits.get(i));
			
			return RArray;
		}

		/// <summary>
		/// Perform the bitwise AND operation on the elements in the current System.Collections.JIBitArray 
		/// against the corresponding elements in the specified System.Collections.JIBitArray
		/// </summary>
		/// <param name="Value">The System.Collections.JIBitArray with which to perform the bitwise OR operation</param>
		/// <returns></returns>
		public JIBitArray And(JIBitArray Value) throws Exception
		{
			JIBitArray RArray;
			int Max = _Bits.size() > Value._Bits.size() ? _Bits.size() : Value._Bits.size();

			RArray = new JIBitArray(Max);
			RArray._Bits = this._Bits;

			if(Max == RArray._Bits.size())
				FixLength(Value,Max);
			else
				FixLength(RArray,Max);

			for(int i=0; i<Max; i++)
				RArray.Set(i,(boolean)Value._Bits.get(i) & (boolean)RArray._Bits.get(i));

			return RArray;
		}

		/// <summary>
		/// Perform the bitwise eXclusive OR operation on the elements in the current System.Collections.JIBitArray 
		/// against the corresponding elements in the specified System.Collections.JIBitArray
		/// </summary>
		/// <param name="Value">The System.Collections.JIBitArray with which to perform the bitwise eXclusive OR operation</param>
		/// <returns></returns>
		public JIBitArray Xor(JIBitArray Value) throws Exception
		{
			JIBitArray RArray;
			int Max = _Bits.size() > Value._Bits.size() ? _Bits.size() : Value._Bits.size();

			RArray = new JIBitArray(Max);
			RArray._Bits = this._Bits;

			if(Max == RArray._Bits.size())
				FixLength(Value,Max);
			else
				FixLength(RArray,Max);

			for(int i=0; i<Max; i++)
				RArray.Set(i,(boolean)Value._Bits.get(i) ^ (boolean)RArray._Bits.get(i));

			return RArray;
		}

		//region Array Convertors
		/// <summary>
		/// Convert current System.Collections.JIBitArray to a long array
		/// </summary>
		/// <returns></returns>
		public long[] GetLong() throws Exception
		{
			int ArrayBound = (int)Math.ceil((double)this._Bits.size()/64);
			long[] Bits = new long[ArrayBound];
			JIBitArray Temp = new JIBitArray();
			Temp._Bits = this._Bits;
			Temp = FixLength(Temp,ArrayBound * 64);
			for(int i=0; i< Temp._Bits.size(); i += 64)
			{
				//Bits[i/64] = Convert.ToInt64(Temp.SubJIBitArray(i,64).ToString(),2);
                                //String st  =Temp.SubJIBitArray(i,64).ToString() ;
                                Bits[i/64] = Long.parseLong(Temp.SubJIBitArray(i,64).ToString());
			}
			return Bits;
                }

        public long[] GetULong() throws Exception
        {
            int ArrayBound = (int)Math.ceil((double)this._Bits.size() / 64);
            long[] Bits = new long[ArrayBound];
            JIBitArray Temp = new JIBitArray();
            Temp._Bits = this._Bits;
            Temp = FixLength(Temp, ArrayBound * 64);
            for (int i = 0; i < Temp._Bits.size(); i += 64)
            {
                //Bits[i / 64] = Convert.ToUInt64(Temp.SubJIBitArray(i, 64).ToString(), 2);
                //Bits[i/64] = Long.parseUnsignedLong(Temp.SubJIBitArray(i,64).ToString(),2);
                Bits[i/64] = Long.parseUnsignedLong(Temp.SubJIBitArray(i,64).ToString());
            }
            return Bits;
        }
        public int[] GetUInt() throws Exception
        {
            int ArrayBound = (int)Math.ceil((double)this._Bits.size() / 32);
            int[] Bits = new int[ArrayBound];
            JIBitArray Temp = new JIBitArray();
            Temp._Bits = this._Bits;
            Temp = FixLength(Temp, ArrayBound * 32);
            for (int i = 0; i < Temp._Bits.size(); i += 32)
            {
                JIBitArray T = Temp.SubJIBitArray(i, 32);
                Bits[i / 32] = Integer.parseUnsignedInt(T.ToString());// .Parse(T.ToString()); // Convert.ToUInt32(T.ToString(), 2);
            }
            return Bits;
        }
        
        public BigInteger GetBigInteger() throws Exception
        {
            
            byte [] buffer  =this.GetBytes() ;
            
            byte[] buffer_inv = new byte[buffer.length] ;
            
            
            for (int i=0 ; i< buffer.length ; i++)
                buffer_inv[i]=buffer[buffer.length-1-i];
            
            return new BigInteger(1, buffer_inv);
            
        }
        
		/// <summary>
		/// Convert current System.Collections.JIBitArray to a int array
		/// </summary>
		/// <returns></returns>
		public int[] GetInt() throws Exception
		{
			int ArrayBound = (int)Math.ceil((double)this._Bits.size()/32);
			int[] Bits = new int[ArrayBound];
			JIBitArray Temp = new JIBitArray();
			Temp._Bits = this._Bits;
			Temp = FixLength(Temp,ArrayBound * 32);
			
			for(int i=0; i< Temp._Bits.size(); i += 32)
			{
                                String SS=Temp.SubJIBitArray(i,32).ToString();
				//Bits[i/32] =Integer.parseInt(Temp.SubJIBitArray(i,32).ToString(),2);
                                Bits[i/32] =(int)Long.parseLong(SS) ; // Integer.parseInt(Temp.SubJIBitArray(i,32).ToString());
			}
			return Bits;
		}

		/// <summary>
		/// Convert current System.Collections.JIBitArray to a short array
		/// </summary>
		/// <returns></returns>
		public short[] GetShorts() throws Exception
		{
			int ArrayBound = (int)Math.ceil((double)this._Bits.size()/16);
			short[] Bits = new short[ArrayBound];
			JIBitArray Temp = new JIBitArray();
			Temp._Bits = this._Bits;
			Temp = FixLength(Temp,ArrayBound * 16);
			
			for(int i=0; i< Temp._Bits.size(); i += 16)
			{
                           
				//Bits[i/16] = Short.parseShort(Temp.SubJIBitArray(i,16).ToString(),2);
                                Bits[i/16] = Short.parseShort(Temp.SubJIBitArray(i,16).ToString());
			}
			return Bits;
		}

		/// <summary>
		/// Convert current System.Collections.JIBitArray to a byte array
		/// </summary>
		/// <returns></returns>
		public byte[] GetBytes() throws Exception
		{
			int ArrayBound = (int)Math.ceil((double)this._Bits.size()/8);
			byte[] Bits = new byte[ArrayBound];
			JIBitArray Temp = new JIBitArray();
			Temp._Bits = this._Bits;
			Temp = FixLength(Temp,ArrayBound * 8);
			
			for(int i=0; i< Temp._Bits.size(); i += 8)
			{
                            //Bits[i / 8] = (byte)Short.parseShort(Temp.SubJIBitArray(i, 8).ToStringBase(), 2);
                            Bits[i / 8] = (byte)Short.parseShort(Temp.SubJIBitArray(i, 8).ToString());
			}
			return Bits;
		}
		

		/// <summary>
		/// Shift the bits of current System.Collections.JIBitArray as specified number to 
		/// left
		/// </summary>
		/// <param name="count">Specific number to shift left</param>
		/// <returns></returns>
		public JIBitArray ShiftLeft(int count)
		{
			JIBitArray RArray = new JIBitArray();
			RArray._Bits = this._Bits;
			for(int i=0; i<count; i++)
			{
				RArray._Bits.remove(0);// RemoveAt(0);
				RArray._Bits.add(false);
			}
			return RArray;
		}

		/// <summary>
		/// Shift the bits of current System.Collections.JIBitArray as specified number to 
		/// right
		/// </summary>
		/// <param name="count">Specific number to shift right</param>
		/// <returns></returns>
		public JIBitArray ShiftRight(int count)
		{
			JIBitArray RArray = new JIBitArray();
			RArray._Bits = this._Bits;
			for(int i=0; i<count; i++)
			{
				RArray._Bits.remove(RArray._Bits.size()-1);//RemoveAt(RArray._Bits.size()-1);
				RArray._Bits.insertElementAt(false, 0);//Insert(0,false);
			}
			return RArray;
		}

		/// <summary>
		/// Remove zero's of begining of current System.Collections.JIBitArray
		/// </summary>
		/// <returns></returns>
		public JIBitArray RemoveBeginingZeros()
		{
			JIBitArray RArray = new JIBitArray();
			RArray._Bits = this._Bits;
			while(RArray._Bits.size() != 0 && (boolean)RArray._Bits.get(0) == false)
				RArray._Bits.remove(0);// RemoveAt(0);
			return RArray;
		}

		
		/// <summary>
		/// Insert enough zero at the begining of the specified System.Collections.JIBitArray to 
		/// make it's lenght to specified length
		/// </summary>
		/// <param name="Value">The System.Collections.JIBitArray with wich to insert zero to begining</param>
		/// <param name="length">The number of bits of Value after inserting</param>
		/// <returns></returns>
		public static JIBitArray FixLength(JIBitArray Value,int length) throws Exception
		{
                        
			if(length < Value._Bits.size())
				throw new Exception("length must be equal or greater than Bits.Length");
                        while (Value._Bits.size() < length)
                            Value._Bits.add(false);// Insert(0, false);
                                    return Value;
		}
		
                /*
		public static JIBitArray operator &(JIBitArray Bits1, JIBitArray Bits2)
		{
			return Bits1.And(Bits2);
		}

		public static JIBitArray operator |(JIBitArray Bits1, JIBitArray Bits2)
		{
			return Bits1.Or(Bits2);
		}

		public static JIBitArray operator ^(JIBitArray Bits1, JIBitArray Bits2)
		{
			return Bits1.Xor(Bits2);
		}

		public static JIBitArray operator >>(JIBitArray Bits, int count)
		{
			return Bits.ShiftRight(count);
		}

		public static JIBitArray operator <<(JIBitArray Bits, int count)
		{
			return Bits.ShiftLeft(count);
		} */

    void Append(byte[] b1, String a1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
		
}
