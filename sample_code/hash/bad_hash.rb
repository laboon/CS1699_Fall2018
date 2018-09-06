raise "Enter a string to hash" unless ARGV.size > 0
to_hash = ARGV[0]
hash_value = ((to_hash.chars.map { |x| x.ord }.sum) % 256).to_i
zero_padded_hash = "%02X" % hash_value
puts "Hash of '#{to_hash}' is: #{zero_padded_hash}"
